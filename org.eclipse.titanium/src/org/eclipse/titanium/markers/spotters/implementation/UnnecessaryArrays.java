package org.eclipse.titanium.markers.spotters.implementation;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.titan.designer.AST.IVisitableNode;
import org.eclipse.titan.designer.AST.TTCN3.definitions.Def_Const;

import org.eclipse.titan.designer.AST.TTCN3.definitions.Def_Var;
import org.eclipse.titan.designer.AST.TTCN3.definitions.Definition;
import org.eclipse.titan.designer.AST.TTCN3.types.Array_Type;
import org.eclipse.titan.designer.parsers.CompilationTimeStamp;
import org.eclipse.titanium.markers.spotters.BaseModuleCodeSmellSpotter;
import org.eclipse.titanium.markers.types.CodeSmellType;
/**
@author T�r�k G�bor
**/
public class UnnecessaryArrays extends BaseModuleCodeSmellSpotter {

	private static final String ERROR_MESSAGE = "Usage of array statement"
			+ " can slow the compile time";
	protected final CompilationTimeStamp timestamp;
	public UnnecessaryArrays() {
			super(CodeSmellType.UNNECESSARY_ARRAYS);
			timestamp = CompilationTimeStamp.getBaseTimestamp();
	}


	@Override
	protected void process(IVisitableNode node, Problems problems) {
		if (!(node instanceof Def_Const) &&
				!(node instanceof Def_Var)) {
			return;
		}
		final Definition s = (Definition)node;
		if(s.getType(timestamp) instanceof Array_Type) {
			problems.report(s.getLocation(), ERROR_MESSAGE);
		}
		
	}

	@Override
	public List<Class<? extends IVisitableNode>> getStartNode() {
		final List<Class<? extends IVisitableNode>> ret = new ArrayList<Class<? extends IVisitableNode>>(1);
		ret.add(Def_Var.class);
		ret.add(Def_Const.class);
		return ret;
	}
	
}
