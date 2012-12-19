package sample.client;

import java.util.List;

import sample.client.dto.CategoryDTO;
import sample.client.dto.GroupDTO;
import sample.client.dto.RatedDTO;
import sample.client.dto.StatsDTO;


import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>RatingService</code>.
 */
public interface RatingServiceAsync {
  void getCategoryRatedStats(String category,
      AsyncCallback<List<StatsDTO>> callback);

  void getCategoryGroupStats(String category,
      AsyncCallback<List<StatsDTO>> callback);

  void getRatedStats(String rated, AsyncCallback<List<StatsDTO>> callback);

  void getGroupStats(String group, AsyncCallback<List<StatsDTO>> callback);

  void loadData(AsyncCallback<List<StatsDTO>> callback);

  void getAllCategories(AsyncCallback<List<CategoryDTO>> callback);

  void getAllRated(AsyncCallback<List<RatedDTO>> callback);

  void getGroup(String groupName, AsyncCallback<GroupDTO> callback);

  void rate(String category, String rated, float score,
      AsyncCallback<Void> callback);
}
