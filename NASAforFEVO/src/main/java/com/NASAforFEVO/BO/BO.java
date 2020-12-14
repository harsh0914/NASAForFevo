package com.NASAforFEVO.BO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.NASAforFEVO.Model.NASAModel;
public class BO {
	@Value("${api.key}")
	private String apikey;

	public void GetExternal(Map<String, List<String>> master, String roverName, String camName, String dateString) {
		String keyString = roverName + camName + dateString;
		RestTemplate rt = new RestTemplate();
		// https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-05-30&camera=fhaz&api_key=DEMO_KEY
		String URI = "https://api.nasa.gov/mars-photos/api/v1/rovers/" + roverName + "/photos?earth_date=" + dateString
				+ "&camera=" + camName + "&api_key=fF8UtGbnbbivOWe0m9nj2iKv4FFwOeH9EgJo3xV2";
		
		System.out.println(URI);
		NASAModel obj = rt.getForObject(URI, NASAModel.class);
		List<String> lst1= new ArrayList<>();
		try {
			lst1.add(obj.photos.get(0).img_src);
			lst1.add(obj.photos.get(1).img_src);
			lst1.add(obj.photos.get(2).img_src);
			master.put(keyString, lst1);
		} catch (Exception e) {

			master.put(keyString, lst1);

		}
	}

	public Map.Entry<String, List<String>> FetchByDate(Map<String, List<String>> master, String roverName, String camName,
			String dateString) {
		String keyString = roverName + camName + dateString;
		if (master.containsKey(keyString)) {
			return Map.entry(dateString, master.get(keyString));
		} else {
			GetExternal(master, roverName, camName, dateString);
			return Map.entry(dateString, master.get(keyString));
		}
	}

	public Map<String, List<String>> LastTenDaysFetcher(Map<String, List<String>> master, String roverName, String camName) {
		Map<String, List<String>> result = new LinkedHashMap<>();

		for (int i = 0; i < 10; i++) {
			String dateString = LocalDate.now().minusDays(i).toString();
			String keyString = roverName + camName + dateString;
			if (master.containsKey(keyString)) {
				result.put(dateString, master.get(keyString));
			}

			else {
				GetExternal(master, roverName, camName, dateString);
				result.put(dateString, master.get(keyString));
			}
		}

		return result;
	}
}
