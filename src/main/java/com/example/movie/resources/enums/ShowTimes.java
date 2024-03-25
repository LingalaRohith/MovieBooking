package com.example.movie.resources.enums;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = ShowTimesSerializer.class)
@JsonDeserialize(using = ShowTimesDeserializer.class)
public enum ShowTimes {
	Morning(10),
	Noon(13),
	PreEvening(16),
	Evening(19),
	Night(22);
	
	public final int time;
	 
	ShowTimes(int time) {
		this.time = time;
	}
	
	public int getTime() { return time; }
}
