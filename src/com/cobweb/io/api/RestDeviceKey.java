/*******************************************************************************
 * Copyright  (c) 2015-2016, Cobweb IO (http://www.cobweb.io) All Rights Reserved.
 *   
 * Cobweb IO licences this file to you under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.cobweb.io.api;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.cobweb.io.service.ReadService;

/**
 * The Class RestDeviceKey.
 * @author Yasith Lokuge
 */

@Path ("/device/key")
public class RestDeviceKey {

	/** The Constant ERROR. */
	private static final String ERROR				= "ERROR";
	
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path ("{deviceId}")
	public String getKey(@PathParam("deviceId") String deviceId){
		
		ReadService readService = new ReadService();
		Subject currentUser = SecurityUtils.getSubject();
		String email = (String) currentUser.getPrincipal();
		String userId = readService.getUserId(email);
		
		List<String> deviceIdList 			= readService.getDeviceIdList(userId);
		
		if(!deviceIdList.contains(deviceId))					
			return ERROR;	
		
		String key = readService.getKeyFromDeviceId(deviceId);
		return key;		
	}
}
