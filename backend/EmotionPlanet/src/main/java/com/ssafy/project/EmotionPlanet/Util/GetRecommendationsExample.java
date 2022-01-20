package com.ssafy.project.EmotionPlanet.Util;

//import com.neovisionaries.i18n.CountryCode;
//import com.wrapper.spotify.SpotifyApi;
//import com.wrapper.spotify.exceptions.SpotifyWebApiException;
//import com.wrapper.spotify.model_objects.specification.Recommendations;
//import com.wrapper.spotify.model_objects.specification.Track;
//import com.wrapper.spotify.requests.data.browse.GetRecommendationsRequest;
//import org.apache.hc.core5.http.ParseException;
//
//import java.io.IOException;
//import java.util.concurrent.CancellationException;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CompletionException;
//
//public class GetRecommendationsExample {
//	private static final String accessToken = "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk";
//
//	private static final SpotifyApi spotifyApi = new SpotifyApi.Builder().setAccessToken(accessToken).build();
//
//	public static void getRecommendations_Sync() {
//		GetRecommendationsRequest getRecommendationsRequest = spotifyApi.getRecommendations().limit(10)
//				.market(CountryCode.KR)
////          .max_popularity(50)
//				.min_popularity(10)
////          .seed_artists("0LcJLqbBmaGUft1e9Mm8HV")
//				.seed_genres("happy")
////          .seed_tracks("01iyCAUm8EvOFqVWYJ3dVX")
////          .target_popularity(20)
//				.build();
//		Track track = null; // spotify에서 제공하는 track
////		Artist artist = null; // spotify에서 제공하는 artist
//		try {
//			final Recommendations recommendations = getRecommendationsRequest.execute();
//			for (int i = 0; i < recommendations.getTracks().length; i++) {
//
//				track = getTrack_Sync(recommendations.getTracks()[i].getId());
////				artist = getArtist_Sync(recommendations.getTracks()[i].getArtists()[0].getId());
//				// 제목, 장르, 가수, 앨범, 미리듣기, 이미지, 인기도, 음악ID, 가수ID, 앨범ID, 추천횟수
//				// 제목
//				System.out.println(recommendations.getTracks()[i].getName());
//				// 장르
////				String genres = "";
////
////				for (String s : artist.getGenres()) {
////					genres = genres.concat(s).concat(",");
////				}
////				System.out.println(genres);
//				// 가수
//				System.out.println(track.getArtists()[0].getName());
//				// 앨범
//				System.out.println(track.getAlbum().getName());
//				// 미리듣기
//				System.out.println(recommendations.getTracks()[i].getPreviewUrl());
//				// 이미지
//				System.out.println(track.getAlbum().getImages()[0].getUrl());
//				// 음악ID
//				System.out.println(recommendations.getTracks()[i].getId());
//				// 가수ID
//				System.out.println(recommendations.getTracks()[i].getArtists()[0].getId());
//				// 앨범ID
//				System.out.println(track.getAlbum().getId());
//				// 인기도
//				System.out.println(String.valueOf(track.getPopularity()));
//				// 발매일
//				System.out.println(track.getAlbum().getReleaseDate());
//
//				System.out.println("제목 : " + recommendations.getTracks()[i].getName() + " 가수 : "
//						+ recommendations.getTracks()[i].getArtists()[0].getName() + " 미리듣기 : "
//						+ recommendations.getTracks()[i].getPreviewUrl() + " 인기도: " + track.getPopularity());
//
////				System.out.println("발매일 : "+track.getAlbum().getReleaseDate());
//			}
//			System.out.println("Length: " + recommendations.getTracks().length);
//		} catch (IOException | SpotifyWebApiException | ParseException e) {
//			System.out.println("Error: " + e.getMessage());
//		}
//	}
//
//	public static Track getTrack_Sync(String id) {
//	    try {
//	      final Track track = getRecommendations_Sync.execute();
//	      return track;
//	    } catch (IOException | SpotifyWebApiException | ParseException e) {
//	      System.out.println("Error: " + e.getMessage());
//	    }
//	  }
//
//	public static void main(String[] args) {
//		getRecommendations_Sync();
//	}
//}