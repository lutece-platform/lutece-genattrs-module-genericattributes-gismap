/*
 * Copyright (c) 2002-2014, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.genericattributes.modules.gismap.service;


import java.util.HashMap;
import java.util.Map;

import fr.paris.lutece.plugins.genericattributes.business.IGismapProvider;
import fr.paris.lutece.plugins.genericattributes.business.IResponseDAO;
import fr.paris.lutece.plugins.genericattributes.business.Response;
import fr.paris.lutece.plugins.gismap.business.AddressParam;
import fr.paris.lutece.plugins.gismap.business.AddressParamHome;
import fr.paris.lutece.plugins.gismap.business.Geometry;
import fr.paris.lutece.plugins.gismap.business.GeometryHome;
import fr.paris.lutece.plugins.gismap.business.IGeometryDAO;
import fr.paris.lutece.plugins.gismap.business.MapParameter;
import fr.paris.lutece.plugins.gismap.business.View;
import fr.paris.lutece.plugins.gismap.business.ViewHome;
import fr.paris.lutece.portal.service.spring.SpringContextService;
import fr.paris.lutece.portal.service.template.AppTemplateService;
import fr.paris.lutece.portal.service.util.AppPropertiesService;
import fr.paris.lutece.util.ReferenceItem;
import fr.paris.lutece.util.html.HtmlTemplate;


/**
 * 
 * GisProvider : provides Gismap support for Generic Attributes
 * 
 */
public class GismapProvider implements IGismapProvider
{
    private static final long serialVersionUID = 6224042984367506762L;
    private static final String PROPERTY_KEY = "genericattributes-gismap.key";
    private static final String PROPERTY_DISPLAYED_NAME = "genericattributes-gismap.displayName";
    private static final String TEMPLATE_HTML = "/admin/plugins/genericattributes/modules/gismap/GismapTemplate.html";
    
    public static final String GISMAP_VIEW_INIT = "gismap.view.GENATT";
    

    /**
     * {@inheritDoc}
     */
    public String getKey( )
    {
        return AppPropertiesService.getProperty( PROPERTY_KEY );
    }

    /**
     * {@inheritDoc}
     */
    public String getDisplayedName( )
    {
        return AppPropertiesService.getProperty( PROPERTY_DISPLAYED_NAME );
    }

    /**
     * {@inheritDoc}
     */
    public String getHtmlCode( )
    {
    	return TEMPLATE_HTML;
    }

    /**
     * {@inheritDoc}
     */
    public ReferenceItem toRefItem( )
    {
        ReferenceItem refItem = new ReferenceItem( );

        refItem.setCode( getKey( ) );
        refItem.setName( getDisplayedName( ) );

        return refItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString( )
    {
        return "Gismap Provider";
    }

	@Override
	public AddressParam getAddParameter() 
	{
		return AddressParamHome.getAddressParameters();
	}

	@Override
	public void createGeometry( Response response) {
		// TODO Auto-generated method stub
		
		Geometry geometry = GeometryHome.findResponseValueByKey("GEO");
		if(response.getIdResponse()==geometry.getId())
		{
			geometry.setThematic("TEST");
			GeometryHome.create(geometry);
		}
		
	}

	@Override
	public MapParameter getViewParameter() {
		// TODO Auto-generated method stub
		String strInitView = AppPropertiesService.getProperty( GISMAP_VIEW_INIT );
        View view = ViewHome.findByPrimaryKey(Integer.parseInt(strInitView));
		return view.getMapParameter();
	}

	@Override
	public String getIncludeFile() {
		// TODO Auto-generated method stub
		String strInitView = AppPropertiesService.getProperty( GISMAP_VIEW_INIT );
        View view = ViewHome.findByPrimaryKey(Integer.parseInt(strInitView));
		return view.getMapTemplateFile();
	}

	

}
