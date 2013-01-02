package sample.client;

import java.util.List;

import sample.shared.Action;
import sample.shared.Response;
import sample.shared.result.CategoryDTO;
import sample.shared.result.GroupDTO;
import sample.shared.result.RatedDTO;
import sample.shared.result.StatsDTO;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("ratingService")
public interface RatingService extends RemoteService {
  
  <R extends Response> R execute(Action<R> action);
  
  //---------------------------------
  
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
