package com.NASAforFEVO.Conroller;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.NASAforFEVO.BO.BO;

@RestController
public class Controller {
	Map<String, List<String>> master = new LinkedHashMap<String, List<String>>(4, .75F, true) {
		protected boolean removeEldestEntry(Map.Entry<String, List<String>> eldest) {
			return size() > 500;
		}

	};

	// http://localhost:8080/api/v1/GetImagesLastTenDays?roverName=Curi&camName=NotNav
	@GetMapping("api/v1/GetImagesLastTenDays")
	public Map<String, List<String>> LastTenDays(
			@RequestParam(value = "roverName", defaultValue = "Curiousity") String roverName,
			@RequestParam(value = "camName", defaultValue = "NAV") String camName) {
		Map<String, List<String>> result = new LinkedHashMap<>();
		BO bo = new BO();
		result = bo.LastTenDaysFetcher(master,roverName,camName);
		return result;
	}

	// http://localhost:8080/api/v1/GetImageByDate?date=2020.12.11
	@GetMapping("api/v1/GetImageByDate")
	public Entry<String, List<String>> ImageByDate(@RequestParam(value = "roverName", defaultValue = "curiosity") String roverName,
			@RequestParam(value = "camName", defaultValue = "navcam") String camName,
			@RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy.MM.dd") LocalDate date) {
		if (date == null) {
			date = LocalDate.now();
		}
		BO bo = new BO();
		return bo.FetchByDate(master,roverName,camName,date.toString());
	}

}
