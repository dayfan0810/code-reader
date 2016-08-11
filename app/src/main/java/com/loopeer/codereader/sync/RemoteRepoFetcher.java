
package com.loopeer.codereader.sync;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.loopeer.codereader.utils.DownloadUrlParser;

public class RemoteRepoFetcher {
    private static final String TAG = "RemoteRepoFetcher";
    private Context mContext;
    private String mUrl;
    private Uri mDestinationUri;
    private String mRepoName;

    public RemoteRepoFetcher(Context context, String url, String repoName) {
        mContext = context;
        mUrl = url;
        mRepoName = repoName;
        mDestinationUri = Uri.fromFile(DownloadUrlParser.getRemoteRepoZipFileName(repoName));
    }

    public long download() {
        DownloadManager manager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri downloadUri = Uri.parse(mUrl);
        DownloadManager.Request request = new DownloadManager.Request(downloadUri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE
                | DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//        request.setVisibleInDownloadsUi(false);
        request.setDescription(mRepoName);
        request.setDestinationUri(mDestinationUri);
        long downloadId = manager.enqueue(request);
        Log.e(TAG, "downloadID:----- " + downloadId);
        return downloadId;
    }

}