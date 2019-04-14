package com.fangcheng.test.converter;

import com.fangcheng.test.entity.TableAuthor;
import com.fangcheng.test.service.TableAuthorService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class RoleToUserProfileConverter implements Converter<Object, TableAuthor> {

	static final Logger logger = LoggerFactory.getLogger(RoleToUserProfileConverter.class);
	
	@Autowired
	TableAuthorService tableAuthorService;

	/**
	 * Gets UserProfile by Id
	 * @see Converter#convert(Object)
	 */
	public TableAuthor convert(Object element) {
		Integer id = Integer.parseInt((String)element);
		TableAuthor tableAuthor= tableAuthorService.findByAuthorId(id);
		logger.info("Profile : {}",tableAuthor);
		return tableAuthor;
	}
	
}