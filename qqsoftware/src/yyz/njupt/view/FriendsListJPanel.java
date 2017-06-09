package yyz.njupt.view;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


import javax.swing.JPanel;

import yyz.njupt.view.netService.Config;
import yyz.njupt.view.netService.POJO.LoginRespProto;

public class FriendsListJPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel
	 */

	public FriendsListJPanel() {

		super();
		setLayout(null);
	}

	public void updateJPanel() {
		Config.map.clear();
		updateFriendsList();
		updateOnline();
		Collection<FaceJPanel> faceJPanels = Config.map.values();
		List<FaceJPanel> list = new ArrayList<>(faceJPanels);
		Collections.sort(list);

		this.removeAll();
		int i = 0;
		for (FaceJPanel faceJPanel : list) {
			faceJPanel.setBounds(0, i++ * 50, 546, 59);
			this.add(faceJPanel);
		}

		this.setPreferredSize(new Dimension(0, 52 * list.size()));
		this.updateUI();
	}


	private void updateFriendsList() {
		List<LoginRespProto.FriendsInfo> friendsList = Config.friendsInfoListResp;
		if (friendsList.size() > 0) {
			for (LoginRespProto.FriendsInfo info : friendsList) {
				Config.map.put(info.getUID(),
						new FaceJPanel(info.getUID(), info.getPetname(), info.getRemark(), info.getImage()));
			}
		}
	}

	private void updateOnline() {
		List<Integer> onlineUIDs = Config.onlineFriendsResp;
		if (onlineUIDs.size() > 0) {
			for (int uid : Config.map.keySet()) {
				if (onlineUIDs.contains(uid)) {
					Config.map.get(uid).updateOnline(true);
				}
			}

		}
	}

}
