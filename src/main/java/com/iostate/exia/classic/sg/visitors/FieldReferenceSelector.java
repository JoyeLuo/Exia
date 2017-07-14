package com.iostate.exia.classic.sg.visitors;


import java.util.HashSet;
import java.util.Set;

import com.iostate.exia.ast.AstUtils;
import org.eclipse.jdt.core.dom.*;


public class FieldReferenceSelector extends ASTVisitor {
	private static final boolean GO = true;
	
	private Set<String> hits = new HashSet<String>();

	public Set<String> getHits() {
		return hits;
	}

	@Override
	public boolean visit(SimpleName node) {
		ASTNode parent = node.getParent();
		if (parent instanceof Type 
				&& AstUtils.pureNameOfType((Type) parent).equals(node.getIdentifier())) {
			return GO;
		}
		if (parent instanceof MethodInvocation && ((MethodInvocation)parent).getName()==node) {
			return GO;
		}
		if (parent instanceof VariableDeclaration && ((VariableDeclaration)parent).getName()==node) {
			return GO;
		}
//		if (parent instanceof BodyDeclaration && ((BodyDeclaration)parent).getName()==node) {
//			return GO;
//		}
		
		hits.add(node.getIdentifier());

		return GO;
	}
}
