package com.spring.blog.service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.blog.entity.Category;
import com.spring.blog.payload.CategoryDTO;
import com.spring.blog.repository.CategoryRepository;
import com.spring.blog.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository categoryRepository;
	private ModelMapper modelMapper;
	
	@Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
		this.categoryRepository = categoryRepository;
		this.modelMapper = modelMapper;
	}



	@Override
	public CategoryDTO addCategory(CategoryDTO categoryDTO) {
		Category category = modelMapper.map(categoryDTO, Category.class);
		Category savedCategory = categoryRepository.save(category);
		
		return modelMapper.map(savedCategory, CategoryDTO.class);
	}

}
