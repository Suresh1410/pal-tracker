package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    @Autowired
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping()
    public ResponseEntity create(@RequestBody TimeEntry timeEntry){
        return new ResponseEntity<>(timeEntryRepository.create(timeEntry), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id")long timeEntryId) {
        ResponseEntity responseEntity;
        TimeEntry timeEntry=timeEntryRepository.find(timeEntryId);
        if(timeEntry == null) {
            responseEntity =new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        }else{
            responseEntity=new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return responseEntity;
    }

    @GetMapping()
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity<>(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") long timeEntryId,@RequestBody TimeEntry expected) {
        ResponseEntity responseEntity;
        TimeEntry timeEntry=timeEntryRepository.update(timeEntryId,expected);
        if (timeEntry== null){
            responseEntity=new ResponseEntity<>(timeEntry, HttpStatus.NOT_FOUND);
        }else{
            responseEntity=new ResponseEntity<>(timeEntry, HttpStatus.OK);
        }
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
