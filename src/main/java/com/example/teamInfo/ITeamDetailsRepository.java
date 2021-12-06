package com.example.teamInfo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TeamDetails;

@Repository
public interface ITeamDetailsRepository extends MongoRepository<TeamDetails, String> {
	TeamDetails findBy_id(String _id);
}
