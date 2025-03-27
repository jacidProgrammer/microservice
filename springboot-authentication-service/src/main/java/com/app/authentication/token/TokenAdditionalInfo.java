package com.app.authentication.token;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.app.authentication.services.IUserTokenInfo;

@Component
public class TokenAdditionalInfo implements TokenEnhancer {
	
	private static final String CURRENT_USER = "currentUser";
	
	@Autowired
	private IUserTokenInfo userTokenInfo;

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		Map<String, Object> additionalInformation = new HashMap<>();
		additionalInformation.put(CURRENT_USER, userTokenInfo.findByUsername(authentication.getName()));
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
		return accessToken;
	}

}
