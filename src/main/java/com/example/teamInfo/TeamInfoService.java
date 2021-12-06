package com.example.teamInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.example.adapter.TeamDetailsAdapter;
import com.example.model.TeamDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TeamInfoService implements ITeamInfoService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITeamDetailsRepository teamRepository;

	@Autowired
	private MongoOperations mongoOperation;

	public TeamDetails saveTeamInfo(TeamDetails uiTeamReq) {

		logger.info("Inside saveTeamInfo");
		try {
			String _id = UUID.randomUUID().toString();
			uiTeamReq.set_id(_id);
			MDC.clear();
			MDC.put("_id", _id);
			logger.info("Genereated Id: " + _id + "for Team: " + uiTeamReq.getTeamName());
			teamRepository.save(TeamDetailsAdapter.saveTeamDetails(uiTeamReq));
			logger.info("Team Details Saved Successfully..");
			TeamDetails dbTeamDetail = teamRepository.findBy_id(uiTeamReq.get_id());
			TeamDetails uiRes = null;
			if (dbTeamDetail != null) {
				uiRes = TeamDetailsAdapter.getTeamDetails(dbTeamDetail);
			}
			return uiRes;
		} catch (Exception exe) {
			logger.error("Error in Saving Db" + exe.getMessage(), exe);
			return null;
		}

	}

	public TeamDetails getTeamInfo(TeamDetails dbTeamDetails) {
		logger.info("Inside getTeamInfo");
		TeamDetails uiTeamRes = new TeamDetails();
		try {
			TeamDetails dbTeamDetail = teamRepository.findBy_id(dbTeamDetails.get_id());
			if (dbTeamDetail != null) {
				uiTeamRes = TeamDetailsAdapter.getTeamDetails(dbTeamDetail);
			}
		} catch (Exception exe) {
			logger.error("Error in retrieving data from DB: " + exe.getMessage(), exe);
		}

		return uiTeamRes;
	}

	public List<TeamDetails> getAllTeams(TeamDetails uiRequest) {
		List<TeamDetails> response = new ArrayList<TeamDetails>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			logger.info("Request from UI: " + mapper.writeValueAsString(uiRequest));
			List<TeamDetails> dbTeamInfo = null;
			if (uiRequest != null && uiRequest.getTeamNo() != null && !(uiRequest.getTeamNo().trim().isEmpty())) {
				this.findTeamByNo(uiRequest.getTeamNo());
			} else if (uiRequest != null && uiRequest.getTeamName() != null
					&& !(uiRequest.getTeamName().trim().isEmpty())) {
				this.findTeamByName(uiRequest.getTeamNo());
			} else {
				dbTeamInfo = teamRepository.findAll(Sort.by(Sort.Order.desc("createdDate")));
			}

			if (dbTeamInfo != null && dbTeamInfo.size() > 0) {
				for (TeamDetails team : dbTeamInfo) {
					response.add(TeamDetailsAdapter.getTeamDetails(team));
				}
			}
		} catch (Exception exe) {

		}

		return response;
	}

	private List<TeamDetails> findTeamByName(String teamNo) {
		Criteria noCriteria = Criteria.where("teamNo").is(teamNo);
		Sort sort = Sort.by(Sort.Order.desc("createdDate"));
		Query query = Query.query(noCriteria).with(sort);

		List<TeamDetails> collectionFromDb = mongoOperation.find(query, TeamDetails.class);
		return collectionFromDb;
	}

	private List<TeamDetails> findTeamByNo(String teamName) {

		Criteria nameCriteria = Criteria.where("teamName").regex(teamName, "i");
		Sort sort = Sort.by(Sort.Order.desc("createdDate"));
		Query query = Query.query(nameCriteria).with(sort);

		List<TeamDetails> collectionFromDb = mongoOperation.find(query, TeamDetails.class);
		return collectionFromDb;

	}
}
