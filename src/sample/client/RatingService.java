package sample.client;

import java.util.List;

import sample.client.dto.CategoryDTO;
import sample.client.dto.GroupDTO;
import sample.client.dto.RatedDTO;
import sample.client.dto.StatsDTO;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("springGwtServices/rating")
public interface RatingService extends RemoteService {
  List<StatsDTO> loadData();  
  
	List<StatsDTO> getCategoryRatedStats(String category);
	
	List<StatsDTO> getCategoryGroupStats(String category);
	
	List<StatsDTO> getRatedStats(String rated);
	
	List<StatsDTO> getGroupStats(String group);
	
	List<CategoryDTO> getAllCategories();
	
	List<RatedDTO> getAllRated();
	
	GroupDTO getGroup(String groupName);
	
	void rate(String category, String rated, float score);
}
