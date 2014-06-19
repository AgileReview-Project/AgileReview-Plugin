/**
 * Copyright (c) 2011, 2012 AgileReview Development Team and others.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License - v 1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Malte Brunnlieb, Philipp Diebold, Peter Reuter, Thilo Rauch
 */
package org.agilereview.editorparser.itexteditor.control;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener which triggers reparsing of editor contents, if they changed in the background
 * @author Malte Brunnlieb (16.06.2014)
 */
public class EditorInputListener implements IPropertyListener {
    
    /**
     * Logger instance
     */
    private static final Logger LOG = LoggerFactory.getLogger(EditorParserExtension.class);
    
    private IEditorPart editorPart;
    /**
     * {@link TagParser} for the target input
     */
    private EditorParserExtension tagParser;
    
    /**
     * Creates a new input listener with the given target parser
     * @param tagParser {@link TagParser} for the target editor
     * @author Malte Brunnlieb (16.06.2014)
     */
    public EditorInputListener(IEditorPart editorPart, EditorParserExtension tagParser) {
        this.editorPart = editorPart;
        this.tagParser = tagParser;
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.ui.IPropertyListener#propertyChanged(java.lang.Object, int)
     * @author Malte Brunnlieb (16.06.2014)
     */
    @Override
    public void propertyChanged(Object source, int propId) {
        if (propId == IWorkbenchPartConstants.PROP_INPUT || propId == IWorkbenchPartConstants.PROP_DIRTY) {
            tagParser.reparse(editorPart);
        }
    }
}
