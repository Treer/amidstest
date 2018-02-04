package amidst.filter.json;

import java.util.List;
import java.util.Optional;

import amidst.documentation.GsonObject;
import amidst.documentation.JsonField;
import amidst.filter.Criterion;
import amidst.filter.criterion.MatchSomeCriterion;

@GsonObject
public class CriterionJsonAnd extends CriterionJsonContainer {
	@JsonField()
	public List<CriterionJson> and;

	public CriterionJsonAnd() {}
	
	@Override
	protected Optional<Criterion<?>> doValidate(CriterionParseContext ctx) {
		return validateList(and, ctx, "and")
				.map(list -> new MatchSomeCriterion(list, list.size()));
	}
}