package com.uiyllong.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uiyllong.entity.Condition;
import com.uiyllong.entity.DinnerTable;
import com.uiyllong.entity.Food;
import com.uiyllong.entity.FoodType;
import com.uiyllong.entity.PageBean;

public class HomeServlet extends BaseServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 前台进入首页显示没有再用餐桌
	 * @param request
	 * @param response
	 * @return
	 */
	public Object home(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		List<DinnerTable> tables = dinnerTableService.getAll();
		request.setAttribute("tables", tables);
		url = request.getRequestDispatcher("/app/index.jsp");
		return url;
	}
	
	/**
	 * 进入主页分页展示菜单信息（菜品 菜系）
	 * @param request
	 * @param response
	 * @return
	 */
	public Object homeList(HttpServletRequest request, HttpServletResponse response) {
		Object url = null;
		//餐桌号
		@SuppressWarnings("unused")
		String tableId = request.getParameter("tableId");
		
		String currentPage = request.getParameter("currentPage");
		String foodType_id = request.getParameter("foodType_id");
		String foodName = request.getParameter("foodName");
		PageBean<Food> pg = new PageBean<Food>();		
		if (currentPage == null || "".equals(currentPage.trim())) {
			currentPage = "1";
		}
		pg.setCurrentPage(Integer.parseInt(currentPage));
		Condition condition = new Condition();
		if (foodType_id == null ||"".equals(foodType_id.trim())) {
			foodType_id = "1";
		}
		condition.setFoodType_id(Integer.parseInt(foodType_id));
		condition.setFoodName(foodName);
		List<FoodType> foodTypes = foodTypeService.list();
		pg.setCondition(condition);
		foodService.pageList(pg);
		request.setAttribute("foodTypes", foodTypes);
		request.setAttribute("foods", pg.getPageData());
		request.setAttribute("pg", pg);
		url = request.getRequestDispatcher("/app/caidan.jsp");
		return url;
	}
	
}