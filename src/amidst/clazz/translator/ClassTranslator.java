package amidst.clazz.translator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import amidst.clazz.real.RealClass;
import amidst.clazz.real.detector.RealClassDetector;
import amidst.clazz.symbolic.declaration.SymbolicClassDeclaration;
import amidst.logging.Log;

public class ClassTranslator {
	public static CTBuilder builder() {
		return CTBuilder.newInstance();
	}

	private Map<RealClassDetector, SymbolicClassDeclaration> translations;

	public ClassTranslator(
			Map<RealClassDetector, SymbolicClassDeclaration> translations) {
		this.translations = translations;
	}

	public Map<SymbolicClassDeclaration, List<RealClass>> translateToAllMatching(
			List<RealClass> realClasses) {
		Map<SymbolicClassDeclaration, List<RealClass>> result = new HashMap<SymbolicClassDeclaration, List<RealClass>>();
		for (Entry<RealClassDetector, SymbolicClassDeclaration> entry : translations
				.entrySet()) {
			SymbolicClassDeclaration declaration = entry.getValue();
			List<RealClass> allMatching = entry.getKey().allMatching(
					realClasses);
			if (result.containsKey(declaration)) {
				result.get(declaration).addAll(allMatching);
			} else {
				result.put(declaration, allMatching);
			}
		}
		return result;
	}

	public Map<SymbolicClassDeclaration, String> translate(
			List<RealClass> realClasses) {
		Map<SymbolicClassDeclaration, String> result = new HashMap<SymbolicClassDeclaration, String>();
		for (Entry<RealClassDetector, SymbolicClassDeclaration> entry : translations
				.entrySet()) {
			RealClass firstMatching = entry.getKey().firstMatching(realClasses);
			String realClassName = null;
			if (firstMatching != null) {
				realClassName = firstMatching.getRealClassName();
			}
			addResult(result, entry.getValue(), realClassName);
		}
		return result;
	}

	private void addResult(Map<SymbolicClassDeclaration, String> result,
			SymbolicClassDeclaration declaration, String realClassName) {
		if (realClassName != null) {
			if (!result.containsKey(declaration)) {
				result.put(declaration, realClassName);
			}
			Log.debug("Found: " + realClassName + " as "
					+ declaration.getSymbolicClassName());
		} else {
			Log.debug("Missing: " + declaration.getSymbolicClassName());
		}
	}
}
