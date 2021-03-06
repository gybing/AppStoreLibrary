package net.sjava.appstore;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * PlayStore class
 *
 * @author mcsong@gmail.com
 * @date Dec 10, 2014 2:04:13 PM
 * @version 1.0.0
 */
public class PlayAppStore extends AppStore implements PublisherAppOpenable {
	private static final String APP_URL = "http://play.google.com/store/apps/deails?id=";
	private static final String APP_SEARCH_URL = "http://play.google.com/store/search?q=";

	public static PlayAppStore newInstance() {
		return new PlayAppStore();
	}

	@Override
	public boolean isInstalled(Context ctx) {
		return isAppInstalled(ctx, PACKAGE_NAME_PLAY_OLD)
				|| isAppInstalled(ctx, PACKAGE_NAME_PLAY_NEW);
	}

	@Override
	public void openApp(Context ctx, String uniqueId) {
		if(isInstalled(ctx)) {
			try {
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + uniqueId));
				ctx.startActivity(intent);
				return;
			} catch (ActivityNotFoundException e) { // ignore
			}
		}

		intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_URL + uniqueId));
		ctx.startActivity(intent);
	}

	@Override
	public void searchApp(Context ctx, String keyword) {
		if(isInstalled(ctx)) {
			try {
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=" + keyword));
				ctx.startActivity(intent);
				return;
			} catch (ActivityNotFoundException e) { // ignore
			}
		}

		intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_SEARCH_URL + keyword));
		ctx.startActivity(intent);
	}

	@Override
	public void openPublisherApps(Context ctx, String key) {
		if(isInstalled(ctx)) {
			try {
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pub:" + key));
				ctx.startActivity(intent);
				return;
			} catch (ActivityNotFoundException e) { // ignore
			}
		}

		intent = new Intent(Intent.ACTION_VIEW, Uri.parse(APP_SEARCH_URL + "pub:" + key));
		ctx.startActivity(intent);
	}

}
