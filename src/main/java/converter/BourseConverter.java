package converter;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("bourseConverter")
public class BourseConverter implements Converter<Double> {

    @Override
    public Double getAsObject(FacesContext context, UIComponent component, String value) {

        if (value == null || value.isEmpty()) {
            return null;
        }

        try {
            value = value.replace(",", ".");

            double montant = Double.parseDouble(value);

            String currency = (String) component.getAttributes().get("currency");

            if (currency == null) {
                return montant;
            }

            switch (currency) {

                case "EUR":
                    return montant * 11;

                case "USD":
                    return montant * 10;

                default:
                    return montant;
            }

        } catch (Exception e) {

            throw new RuntimeException("Erreur conversion bourse : " + value);
        }
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Double value) {

        if (value == null) {
            return "";
        }

        String currency = (String) component.getAttributes().get("currency");

        double montant = value;

        if (currency != null) {

            switch (currency) {

                case "EUR":
                    montant = value / 11;
                    break;

                case "USD":
                    montant = value / 10;
                    break;

            }

        }

        return String.format("%.2f", montant);
    }
}
