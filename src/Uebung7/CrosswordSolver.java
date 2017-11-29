public class CrosswordSolver {

	public static CrosswordGrid solve(CrosswordSpec spec) {
		CrosswordGrid gridSolution = new CrosswordGrid(spec);
			if(Helper(gridSolution, new boolean[spec.getWordsSpecLength()], 0, spec)){
				return gridSolution;
			}
			
			return new CrosswordGrid(spec);
	}

	private static boolean Helper(CrosswordGrid gridSolution, boolean[] bs, int i, CrosswordSpec spec) {
		if(bs.length <= i){
			for(boolean b : bs){
				if(!b){
					return false;
				}
			}
			return true;
		}
		
		
		for(int j = 0; j < bs.length; j++){
			if(!bs[j] && gridSolution.isWordValid(spec.getFieldsSpec(i), spec.getWordsSpec(j))){
				boolean[] used = gridSolution.setWord(spec.getFieldsSpec(i), spec.getWordsSpec(j));
				bs[j] = true;
				if(Helper(gridSolution, bs, i+1, spec)){
					return true;
				}
				gridSolution.removeWord(spec.getFieldsSpec(i), spec.getWordsSpec(j), used);
				bs[j] = false;
			}
		}
		
		return false;
	}

}
