package org.cafetownsend.atf.test.steps.actions;

import org.cafetownsend.atf.ui.config.UIScenarioContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.testmonkeys.maui.core.page.Page;

@Component
public class PageActions {

    @Autowired
    private UIScenarioContext context;

    public void openPage(String pageName) {
       getPage(pageName).open();
    }

    public void openPage(Class<? extends Page> pageClass) {
        getPage(pageClass).open();
    }

    public boolean pageIsOpened(String pageName){
        return getPage(pageName).isCurrentPage();
    }

    public boolean pageIsOpened(Class<? extends Page> pageClass){
        return getPage(pageClass).isCurrentPage();
    }

    public Page getPage(String name){
        Page page = context.getPageFactory().createPage(name);

        if (page == null)
            throw new NullPointerException("No page with name [" + name + "] found.");

        return page;
    }

    public Page getPage(Class<? extends Page> clazz){
        Page page = context.getPageFactory().createPage(clazz);

        if (page == null)
            throw new NullPointerException("No such page:" + clazz);

        return page;
    }
}
