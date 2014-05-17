package com.criticalys.wamandroid;

import android.os.Bundle;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;


public class CustomYouTubePlayerSupportFragment extends
		YouTubePlayerSupportFragment {
	
	    public YouTubePlayer activePlayer;

	    public static CustomYouTubePlayerSupportFragment newInstance(String url) {

	    	CustomYouTubePlayerSupportFragment playerYouTubeFrag = new CustomYouTubePlayerSupportFragment();
	        Bundle bundle = new Bundle();
	        bundle.putString("url", url);
	        playerYouTubeFrag.setArguments(bundle);
	        return playerYouTubeFrag;
	    }

	    public void init() {

	        initialize(WamConstants.YOUTUBE_APP_KEY, new OnInitializedListener() {

	            @Override
	            public void onInitializationFailure(Provider arg0, YouTubeInitializationResult arg1) { }

	            @Override
	            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
	                activePlayer = player;
	                activePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
	                activePlayer.setShowFullscreenButton(false);
	                if (!wasRestored) {
	                	activePlayer.setFullscreen(true);
	                	activePlayer.loadVideo(getArguments().getString("url"), 0);
	                }
	            }
	        });
	    }

	    
}
