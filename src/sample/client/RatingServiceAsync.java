package sample.client;

import java.util.List;

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
}
