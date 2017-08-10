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

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.greenvulcano.gestpay.wscryptdecrypt.model.Encrypt;
import it.greenvulcano.gestpay.wscryptdecrypt.model.EncryptResponse.EncryptResult;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecrypt;
import it.greenvulcano.gestpay.wscryptdecrypt.model.WSCryptDecryptSoap;

/**
 * 
 * @version 4.0 august/2017
 * @author GreenVulcano Developer Team
 */
public class EncryptOperation {

	private static final Logger logger 	= LoggerFactory.getLogger(EncryptOperation.class);
	
	public EncryptOperation() {
		logger.info("Created Encrypt Operation");
	}
	
	/**
	 * 
	 * @param data
	 * @param wsCryptDecryp
	 * @return
	 */
	public String encryptService(String data, WSCryptDecrypt wsCryptDecryp) {

		String jsonResponse = null;
		
		try {
			ObjectMapper mapper = new ObjectMapper();
		
			Encrypt encrypt = mapper.readValue(data, Encrypt.class);
			
			WSCryptDecryptSoap wsCryptDecryptSoap = wsCryptDecryp.getWSCryptDecryptSoap();
			EncryptResult result = wsCryptDecryptSoap.encrypt(
					encrypt.getShopLogin(), 
					encrypt.getUicCode(), 
					encrypt.getAmount(), 
					encrypt.getShopTransactionId(), 
					encrypt.getCardNumber(),
					encrypt.getExpiryMonth(),
					encrypt.getExpiryYear(),
					encrypt.getBuyerName(),
					encrypt.getBuyerEmail(),
					encrypt.getLanguageId(),
					encrypt.getCvv(),
					encrypt.getCustomInfo(),
					encrypt.getRequestToken(),
					encrypt.getPpSellerProtection(),
					encrypt.getShippingDetails(),
					encrypt.getPaymentTypes(),
					encrypt.getPaymentTypeDetail(),
					encrypt.getRedFraudPrevention(),
					encrypt.getRedCustomerInfo(),
					encrypt.getRedShippingInfo(),
					encrypt.getRedBillingInfo(),
					encrypt.getRedCustomerData(),
					encrypt.getRedCustomInfo(),
					encrypt.getRedItems(),
					encrypt.getConselMerchantPro(),
					encrypt.getConselCustomerInfo(),
					encrypt.getPayPalBillingAgreementDescription(),
					encrypt.getOrderDetails());
			
			jsonResponse = mapper.writeValueAsString(result);
		
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return jsonResponse;
	}

}
