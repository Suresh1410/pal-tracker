package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long,TimeEntry> timeEntryMap=new HashMap();
    Long count=0L;

    public TimeEntry create(TimeEntry timeEntry) {
        long localcount=++count;
        timeEntry.setId(localcount);
        timeEntryMap.put(localcount,timeEntry);
        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntryMap.get(id);
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntry1=timeEntryMap.get(id);
        if (timeEntry1!= null) {
            timeEntry.setId(id);
            timeEntry1.setProjectId(timeEntry.getProjectId());
            timeEntry1.setUserId(timeEntry.getUserId());
            timeEntry1.setDate(timeEntry.getDate());
            timeEntry1.setHours(timeEntry.getHours());
        }
        return timeEntry1;
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntryList=new ArrayList();
        for (Map.Entry<Long,TimeEntry> entry :  timeEntryMap.entrySet()){
            timeEntryList.add(entry.getValue());
        }
        return timeEntryList;
    }

    public void delete(long id) {
        timeEntryMap.remove(id);
    }
}
