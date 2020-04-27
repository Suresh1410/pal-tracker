package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public interface TimeEntryRepository {
    public TimeEntry create(TimeEntry timeEntryToCreate);

    public TimeEntry find(long timeEntryId) ;

    public List<TimeEntry> list() ;

    public TimeEntry update(long eq, TimeEntry any) ;

    public void delete(long timeEntryId);
}
