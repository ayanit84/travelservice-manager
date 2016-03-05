package com.infosys.hackathon.travelservice.json.containers;

import java.util.List;

import com.infosys.hackathon.services.JsonContainer;
import com.infosys.hackathon.services.dos.OnsiteActivity;

public class OnsiteActivityContainer implements JsonContainer {
	private List<OnsiteActivity> onsiteActivity;

	public OnsiteActivityContainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OnsiteActivityContainer(List<OnsiteActivity> onsiteActivity) {
		super();
		this.onsiteActivity = onsiteActivity;
	}

	public List<OnsiteActivity> getOnsiteActivity() {
		return onsiteActivity;
	}

	public void setOnsiteActivity(List<OnsiteActivity> onsiteActivity) {
		this.onsiteActivity = onsiteActivity;
	}

}
