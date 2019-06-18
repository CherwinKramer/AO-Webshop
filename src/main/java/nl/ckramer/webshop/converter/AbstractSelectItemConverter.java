package nl.ckramer.webshop.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public abstract class AbstractSelectItemConverter implements Converter {
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && !value.equals("")) {
			for (SelectItem item : getSelectItemList(component)) {
				Integer hashCode = Integer.valueOf(value);
				if (item.getValue().hashCode() == hashCode) {
					return item.getValue();
				}
			}
			return null;
		} else {
			return null;
		}
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null && !value.equals("")) {
			Integer hashCode = value.hashCode();
			return Integer.toString(hashCode);
		} else {
			return "";
		}
	}

	@SuppressWarnings("unchecked")
	private List<SelectItem> getSelectItemList(UIComponent component) {
		for (UIComponent c : component.getChildren()) {
			if (c instanceof UISelectItems) {
				UISelectItems items = (UISelectItems) c;
				return (List<SelectItem>) items.getValue();
			}
		}
		throw new RuntimeException("Could not find SelectItem in component: " + component);
	}

}