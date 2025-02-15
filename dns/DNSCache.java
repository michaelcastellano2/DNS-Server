package dns;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class representing a cache of stored DNS records.
 *
 * @version 1.0
 */
public class DNSCache {
    // List of all of the cached DNS records
    private ArrayList<DNSRecord> records;

    /*
     * Class constructor
     */
    public DNSCache() {
    	records = new ArrayList<DNSRecord>(); 
    }

    /*
     * Adds all of the DNSRecord answers to the records cache
     */
    public void add(DNSMessage query) {
	ArrayList<DNSRecord> answers = query.getAnswers(); 
	records.addAll(answers);
    }

    /*
     * Removes records from the cache once their TTL expires. 
     */
    public void updateCache() {
	// We need an iterator to avoid a ConcurrentModificationException
	Iterator<DNSRecord> iterator = records.iterator();

	while (iterator.hasNext()) {
		DNSRecord record = iterator.next();

		long elapsedTime = Instant.now().getEpochSecond() - record.getTimeStamp().getEpochSecond();
		if (elapsedTime > record.getTTL()) {
			iterator.remove();
		}
	}
    }

    /*
     * Returns an array list of records that match a given name, type, and class
     */
    public ArrayList<DNSRecord> getRecords(String name, String type, String rclass) {
	ArrayList<DNSRecord> matches = new ArrayList<DNSRecord>();
	
	for (DNSRecord record : records) {
		if (record.getName().equals(name) &&
		    record.getTypeStr().equals(type) &&
	    	    record.getClassStr().equals(rclass)) {
			matches.add(record);				
		    }	
	}

	return matches;
    }
}
