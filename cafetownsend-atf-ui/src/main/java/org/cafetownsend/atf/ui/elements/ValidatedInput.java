package org.cafetownsend.atf.ui.elements;

import org.testmonkeys.maui.pageobjects.elements.Input;
import org.testmonkeys.maui.pageobjects.elements.html.HtmlAttribute;

import java.util.List;
import java.util.Optional;

public class ValidatedInput extends Input {

    public boolean hasValidationErrors() {
        List<HtmlAttribute> attributes = this.getHtmlElement().getAttributes();
        Optional<HtmlAttribute> clazz = attributes.stream().filter(a -> a.getName().equals("class")).findFirst();

        return clazz.map(htmlAttribute -> htmlAttribute.getValue().contains("ng-invalid")).orElse(false);
    }
}
