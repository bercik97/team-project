package com.letswork.api.app.category.domain

import com.letswork.api.app.category.domain.exception.InvalidCategoryException
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

import java.util.concurrent.ConcurrentHashMap

class CategorySpec extends Specification {

    @Shared
    private CategoryFacade categoryFacade

    @Shared
    private ConcurrentHashMap<String, CategoryEntity> db

    def setupSpec() {
        db = new ConcurrentHashMap<>()
        categoryFacade = new CategoryConfiguration().categoryFacade(db)
        addCategory()
    }

    private addCategory() {
        db.put('Marketing', new CategoryEntity('Marketing'))
    }

    def 'Should find category by category name'() {
        given:
        String categoryName = 'Marketing'

        when: 'we try to find category by category name'
        CategoryEntity category = categoryFacade.findByCategoryName(categoryName)

        then: 'category is found'
        category != null && category.getName() == categoryName
    }

    @Unroll
    def 'Should throw an exception cause category name is null, empty or blank = [#categoryName]'(String categoryName) {
        when: 'we try to find a category'
        categoryFacade.findByCategoryName(categoryName)

        then: 'exception is thrown'
        InvalidCategoryException exception = thrown()
        exception.message == InvalidCategoryException.CAUSE.CATEGORY_NAME_EMPTY.message

        where:
        categoryName | _
        null         | _
        ''           | _
        '  '         | _
    }

    def 'Should throw an exception cause category name not exists'() {
        given:
        String categoryName = 'absolutely non existing category name'

        when: 'we try to find category by category name'
        categoryFacade.findByCategoryName(categoryName)

        then: 'exception is thrown'
        InvalidCategoryException exception = thrown()
        exception.message == InvalidCategoryException.CAUSE.CATEGORY_NAME_NOT_EXISTS.message
    }
}
