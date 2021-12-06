package com.example.teamInfo;

import java.util.List;

import com.example.model.TeamDetails;

public interface ITeamInfoService {

	public TeamDetails saveTeamInfo(TeamDetails uiTeamReq);

	public TeamDetails getTeamInfo(TeamDetails dbTeamDetails);

	public List<TeamDetails> getAllTeams(TeamDetails uiReq);
}
