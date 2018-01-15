package com.wrapper.spotify.requests.data.follow;

import com.wrapper.spotify.ITest;
import com.wrapper.spotify.TestUtil;
import com.wrapper.spotify.enums.ModelObjectType;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CheckCurrentUserFollowsArtistsOrUsersRequestTest implements ITest<Boolean[]> {
  private final CheckCurrentUserFollowsArtistsOrUsersRequest successRequest = SPOTIFY_API
          .checkCurrentUserFollowsArtistsOrUsers(ModelObjectType.ARTIST, new String[]{"id"})
          .setHttpManager(
                  TestUtil.MockedHttpManager.returningJson(
                          "requests/data/follow/CheckCurrentUserFollowsArtistsOrUsersRequest.json"))
          .build();

  public CheckCurrentUserFollowsArtistsOrUsersRequestTest() throws Exception {
  }

  @Test
  public void shouldSucceed_sync() throws IOException, SpotifyWebApiException {
    shouldSucceed(successRequest.execute());
  }

  @Test
  public void shouldSucceed_async() throws ExecutionException, InterruptedException {
    shouldSucceed((Boolean[]) successRequest.executeAsync().get());
  }

  public void shouldSucceed(final Boolean[] booleans) {
    assertEquals(
            3,
            booleans.length);
  }
}
