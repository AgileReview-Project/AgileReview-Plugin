/**
 * Copyright (c) 2011, 2012 AgileReview Development Team and others.
 * All rights reserved. This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License - v 1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * Contributors: Malte Brunnlieb, Philipp Diebold, Peter Reuter, Thilo Rauch
 */
package org.agilereview.ui.basic.commentSummary.handler;

import java.util.Iterator;

import org.agilereview.common.exception.ExceptionHandler;
import org.agilereview.common.ui.PlatformUITools;
import org.agilereview.core.external.exception.NoOpenEditorException;
import org.agilereview.core.external.storage.Comment;
import org.agilereview.core.external.storage.CommentingAPI;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;

/**
 * Handler for the delete command of comments in the comments summary view
 * @author Malte Brunnlieb (17.12.2012)
 */
public class DeleteCommentHandler extends AbstractHandler {
    
    /* (non-Javadoc)
     * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
     * @author Malte Brunnlieb (17.12.2012)
     */
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        
        ISelection selection = PlatformUITools.getActiveWorkbenchPage().getSelection();
        if (selection instanceof IStructuredSelection) {
            CommentingAPI api = new CommentingAPI();
            Iterator<?> it = ((IStructuredSelection) selection).iterator();
            while (it.hasNext()) {
                Object o = it.next();
                if (o instanceof Comment) {
                    try {
                        api.deleteComment(((Comment) o).getId());
                    } catch (NoOpenEditorException e) {
                        ExceptionHandler
                                .warnUser("Wrong usage of Commenting API as currently there is no open editor the comment can be deleted from.");
                    }
                }
            }
            
        }
        
        return null;
    }
    
}
