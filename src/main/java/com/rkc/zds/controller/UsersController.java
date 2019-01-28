package com.rkc.zds.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rkc.zds.dto.ContactDto;
import com.rkc.zds.dto.GroupDto;
import com.rkc.zds.dto.Profile;
import com.rkc.zds.dto.UserDto;
import com.rkc.zds.rsql.CustomRsqlVisitor;
import com.rkc.zds.service.UserService;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://www.zdslogic-development.com:4200")
@RestController
@RequestMapping(value = "/api")
public class UsersController {
    @Autowired
    private UserService userService;
    
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<UserDto>> findAllUsers(Pageable pageable, HttpServletRequest req) {
		Page<UserDto> page = userService.findUsers(pageable);
		ResponseEntity<Page<UserDto>> response = new ResponseEntity<>(page, HttpStatus.OK);
		return response;
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDto> getUser(@PathVariable int id, HttpServletRequest req) {
		UserDto user = userService.getUser(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String deleteUser(@PathVariable int id) {
		userService.deleteUser(id);
		return Integer.toString(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/users", method = RequestMethod.PUT, consumes = {
			"application/json;charset=UTF-8" }, produces = { "application/json;charset=UTF-8" })
	public void updateUser(@RequestBody String jsonString) {
		ObjectMapper mapper = new ObjectMapper();

		UserDto user = new UserDto();
		try {
			user = mapper.readValue(jsonString, UserDto.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		userService.updateUser(user);

	}

    @RequestMapping("/users/profiles")
    public List<String> getProfiles(){
        List<String> profiles = new ArrayList<>();
        for(Profile profile: Profile.values()){
            profiles.add(profile.name());
        }
        return profiles;
    }
    
	@RequestMapping(value = "/users/search", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<UserDto>> findAllByRsql(Pageable pageable, @RequestParam(value = "search") String search) {
	    Node rootNode = new RSQLParser().parse(search);
	    Specification<UserDto> spec = rootNode.accept(new CustomRsqlVisitor<UserDto>());
	    //return dao.findAll(spec);
		Page<UserDto> page = userService.searchUsers(pageable, spec);
		return new ResponseEntity<>(page, HttpStatus.OK);
	}
}
