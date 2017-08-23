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

import it.greenvulcano.gestpay.wss2s.model.CallPagamS2S;
import it.greenvulcano.gestpay.wss2s.model.CallPagamS2SResponse.CallPagamS2SResult;
import it.greenvulcano.gestpay.wss2s.model.WSs2S;
import it.greenvulcano.gestpay.wss2s.model.WSs2SSoap;
import it.greenvulcano.util.json.JSONUtils;
import it.greenvulcano.util.json.JSONUtilsException;

/**
 * 
 * @version 4.0 august/2017
 * @author GreenVulcano Developer Team
 */
public class MakePayment {
	
	private static final Logger logger 	= LoggerFactory.getLogger(MakePayment.class);
	
	public MakePayment() {
		logger.info("Created Payment Operation");
	}
	
	/**
	 * 
	 * @param data
	 * @param wss2s
	 * @return
	 */
	public String paymentService(String data, WSs2S wss2s) {
		
		String jsonResponse = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			CallPagamS2S pagam = mapper.readValue(data, CallPagamS2S.class);
			
			WSs2SSoap wsS2sSoap = wss2s.getWSs2SSoap();
			CallPagamS2SResult result = wsS2sSoap.callPagamS2S(
					pagam.getShopLogin(), 
					pagam.getUicCode(), 
					pagam.getAmount(), 
					pagam.getShopTransactionId(), 
					pagam.getCardNumber(), 
					pagam.getExpiryMonth(), 
					pagam.getExpiryYear(), 
					pagam.getBuyerName(), 
					pagam.getBuyerEmail(), 
					pagam.getLanguageId(), 
					pagam.getCvv(), 
					pagam.getMin(), 
					pagam.getTransKey(), 
					pagam.getPARes(), 
					pagam.getCustomInfo(), 
					pagam.getIDEA(), 
					pagam.getRequestToken(), 
					pagam.getTokenValue(), 
					pagam.getClientIP(), 
					pagam.getItemType(), 
					pagam.getRecurrent(), 
					pagam.getSubMerchantId(), 
					pagam.getShippingDetails(), 
					pagam.getRedFraudPrevention(), 
					pagam.getRedCustomerInfo(), 
					pagam.getRedShippingInfo(), 
					pagam.getRedBillingInfo(), 
					pagam.getRedCustomerData(), 
					pagam.getRedCustomInfo(), 
					pagam.getRedItems(), 
					pagam.getApplePay(), 
					pagam.getOrderDetails());
			
			Element element = (Element) result.getContent().get(0);
			jsonResponse = JSONUtils.xmlToJson(element).getJSONObject("GestPayS2S").toString();
			
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
