package com.example.teamInfo;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.TeamDetails;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class TeamInfoController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ITeamInfoService service;

	@ApiOperation(value = "Save Team Detail")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Team Details Collection"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/saveTeamInfo", method = RequestMethod.POST)
	public ResponseEntity<String> saveTeamInfo(@RequestBody TeamDetails teamReq, HttpServletRequest httpReq) {
		TeamDetails response = service.saveTeamInfo(teamReq);
		if (response == null) {
			return (ResponseEntity<String>) ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(response.get_id());

	}

	@ApiOperation(value = "Get Team Details with id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get Team Details Collection"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/getTeamInfo", method = RequestMethod.POST)
	public ResponseEntity<TeamDetails> getTeamInfo(@RequestBody TeamDetails teamReq, HttpServletRequest httpReq) {
		TeamDetails response = service.getTeamInfo(teamReq);
		if (response == null) {
			return (ResponseEntity<TeamDetails>) ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(response);

	}

	@ApiOperation(value = "Get All Team Details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Get All Team Details Collection"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/getAllTeamInfo", method = RequestMethod.POST)
	public ResponseEntity<List<TeamDetails>> getAllTeamInfo(@RequestBody TeamDetails teamReq,
			HttpServletRequest httpReq) {
		List<TeamDetails> response = service.getAllTeams(teamReq);
		if (response == null) {
			return (ResponseEntity<List<TeamDetails>>) ResponseEntity.badRequest();
		}
		return ResponseEntity.ok(response);

	}
}
