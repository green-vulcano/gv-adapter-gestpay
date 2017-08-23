/*******************************************************************************
 * Copyright (c) 2009, 2017 GreenVulcano ESB Open Source Project.
 * All rights reserved.
 *
 * This file is part of GreenVulcano ESB.
 *
 * GreenVulcano ESB is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * GreenVulcano ESB is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with GreenVulcano ESB. If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package it.greenvulcano.gvesb.virtual.gv_gestpay.operation;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.greenvulcano.gestpay.wscryptdecrypt.model.Decrypt;
import it.greenvulcano.gestpay.wscryptdecrypt.model.DecryptResponse.DecryptResult;
import it.greenvulcano.util.json.JSONUtils;
import it.greenvulcano.util.json.JSONUtilsException;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecrypt;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecryptSoap;

/**
 * 
 * @version 4.0 august/2017
 * @author GreenVulcano Developer Team
 */
public class DecryptOperation {

	private static final Logger logger 	= LoggerFactory.getLogger(DecryptOperation.class);
	
	public DecryptOperation() {
		logger.info("Created Decrypt Operation");
	}
	
	/**
	 * 
	 * @param data
	 * @param wsCryptDecryp
	 * @return
	 */
	public String decryptService(String data, WSCryptDecrypt wsCryptDecryp) {
		
		String jsonResponse = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			Decrypt decrypt = mapper.readValue(data, Decrypt.class);
			
			WSCryptDecryptSoap wsCryptDecryptSoap = wsCryptDecryp.getWSCryptDecryptSoap();
			DecryptResult result = wsCryptDecryptSoap.decrypt(
					decrypt.getShopLogin(), 
					decrypt.getCryptedString());
			
			Element element = (Element) result.getContent().get(0);
			jsonResponse = JSONUtils.xmlToJson(element).getJSONObject("GestPayCryptDecrypt").toString();
			
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONUtilsException e) {
			e.printStackTrace();
		}
		
		return jsonResponse;
	}
}
