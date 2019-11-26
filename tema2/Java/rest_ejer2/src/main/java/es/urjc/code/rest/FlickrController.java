package es.urjc.code.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class FlickrController {

	//Please use your own Flickr API Key. This is a fake API key
	private static final String FLICKR_API_KEY = "XXXXXX";

	@GetMapping("flickr_search")
	public String flickrSearch(Model model, @RequestParam String search) throws UnsupportedEncodingException, RestClientException, URISyntaxException { 
		
		String url = "https://api.flickr.com/services/rest/"+
				"?api_key="+FLICKR_API_KEY+"&format=json"+
				"&method=flickr.photos.search&text="+URLEncoder.encode(search, "UTF-8")+"&nojsoncallback=?";
		
		RestTemplate rest = new RestTemplate();
		
		ObjectNode data = rest.getForObject(new URI(url), ObjectNode.class);
		
		model.addAttribute("search",search);
				
		//System.out.println(data);
		
		List<String> photos = new ArrayList<>();
		model.addAttribute("photos",photos);
		
		//https://farm8.staticflickr.com/7250/27919046722_d587301a91_m.jpg
		
		ObjectNode photosObj = (ObjectNode) data.get("photos");
		ArrayNode photosArray = (ArrayNode) photosObj.get("photo");
				
		for (int i = 0; i < photosArray.size(); i++) {
			JsonNode photo = photosArray.get(i);
			
			String farm = photo.get("farm").asText();
			String server = photo.get("server").asText();
			String id = photo.get("id").asText();
			String secret = photo.get("secret").asText();
			
			String photoUrl = "https://farm"+farm+".staticflickr.com/"+server+"/"+id+"_"+secret+"_m.jpg";
			
			photos.add(photoUrl);			
		}
		
		return "flickr_search";
	}

}
