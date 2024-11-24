// collect sammelt immer alle Daten zusammen (also auf den Hauptrechner/Ursprungsrechner dann)

// aggregate angucken 

package loganalyse

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row
import java.time.OffsetDateTime
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import loganalyse._


object LogAnalyseFuns {
  
  var conf:org.apache.spark.SparkConf=_
  var sc:SparkContext=_
  var parsed_logs:RDD[(Row,Int)]= _
  var access_logs:RDD[Row]= _
  var failed_logs:RDD[Row]= _
  
  def getParsedData(data:RDD[String]):(RDD[(Row,Int)],RDD[Row],RDD[Row])={
    
      val parsed_logs= data.map(Utilities.parse_line(_))
      val access_logs= parsed_logs.filter(_._2==1).map(_._1).cache()
      val failed_logs= parsed_logs.filter(_._2==0).map(_._1)
      (parsed_logs, access_logs, failed_logs)
  }
  
    /* 
   * Calculate for the content size the following values:
   * 
   * minimum: Minimum value
   * maximum: Maximum value
   * average: Average
   * 
   * Return the following triple:
   * (min,max,avg)
   */
  def calculateLogStatistic(data:RDD[Row]):(Long,Long,Double)= {
     
    //val max = data.reduce((acc,value)=> {if(acc.get(8,0) < value.get(8)) value else acc})
    val onlyValues = data.map(x=>x.getInt(8).toDouble)
    val max = onlyValues.fold(0)((highestValue: Double, elementNow: Double) => if(highestValue < elementNow) elementNow else highestValue)
    // bei fold[left] einfach immer vorstellen, dass die Basis (hier 0) ganz am Anfang immer dem ersten Para der reduce function gleich gesetzt wird
        val min = onlyValues.fold(0)((minValue: Double, elementNow: Double) => if(minValue > elementNow) elementNow else minValue)
        val elementsInList=onlyValues.toArray().length
        val average = onlyValues.map(y=>y*(1.toDouble/elementsInList)).reduce((a:Double, b:Double) => a+b)

    (min.toLong,max.toLong,average.toLong)
  }
  
  /** Steven:
   * val c = data.map(_.getInt(8))
   * (c.min,c.max,c.mean().tolong)
   * 
   * Gärtner:
   * val content_sizes = data.map(_.getInt(8).toLong).cache() -> cached cached rdd zwischen 
   * (
   * content_sizes.min()
   * content_sizes.max()
   * content_sizes.reduce((X,Y) => X+Y) / content_sizes.count
   * )
   * 
   */
  
 
  
  // es gibt auch min, max, average funktionen von scala, wie man das Ordering macht, siehe Vorlesung


  
    /* 
   * Calculate for each single response code the number of occurences
   * Return a list of tuples which contain the response code as the first
   * element and the number of occurences as the second.
   *    *   
   *    
   *    
   *    
   *    //also wenn map über eine map geht ist x immer (key,value)
    //val map_result = group.map(x => (x._1._1,x._2.length))
   *    
   */
  def getResponseCodesAndFrequencies(data:RDD[Row]):List[(Int,Int)]= {
    data.groupBy(x=>x.getInt(7)).map(x=> (x._1,x._2.count(x=>true))).collect().toList
  }
  
  // count ist lazy loaded
  // ist das selbe wie .toList.length nur in lazyloaded
  
    /** Gärtner
   * val res = data.map(X=>(X.getInt(7),1)).reduceByKey(_+_).cache
   * res.collect.toList
   * 
   */
  
   
    

  
    /* 
   * Calculate 20 abitrary hosts from which the web server was accessed more than 10 times 
   * Print out the result on the console (no tests take place)
   */
  
  def get20HostsAccessedMoreThan10Times(data:RDD[Row]):List[String]= {
    // filter hat Laufbedingung d.h. man wählt die aus, die man haben will
    data.groupBy(x=>x.getString(0)).map(y=>(y._1,y._2.toArray.length)).filter(z=> z._2 >10).keys.take(20).toList
  }
  
  
    /** 
   * 
   * 
   */
  
    /* 
   * Calcuclate the top ten endpoints. 
   * Return a list of tuples which contain the path of the endpoint as the first element
   * and the number of accesses as the second
   * The list should be ordered by the number of accesses.
   */
  
  def getTopTenEndpoints(data:RDD[Row]):List[(String, Int)]= {
    data.groupBy(x=>x.getString(5)).map(y=>(y._1,y._2.toArray.length)).sortBy(z=>z._2,ascending = false,4).take(10).toList
    }                                                                              // eig reicht sortBy(z=>z._2) glaube
  /**
   * val top10 = endpoints.takeOrdered(10)(Ordering.by[(String,Int),Int](Key => -(1)*Key._2))
   */
    

  
    /* 
   * Calculate the top ten endpoint that produces error response codes (response code != 200).
   * 
   * Return a list of tuples which contain the path of the endpoint as the first element
   * and the number of errors as the second.
   * The list should be ordered by the number of accesses.
   */
      
  def getTopTenErrorEndpoints(data:RDD[Row]):List[(String, Int)]= {
    // return of group by: map: mit x._1 = key x._2 = iteratable row
    // return of group by: Map mit key -> Iteratable von allen Rows für die das group by zutraf
    data.groupBy(x=>x.getString(5)).map(y=>(y._1,y._2.map(z=>z.getInt(7)).filter(a=>a!=200).toArray.length)).sortBy(z=>z._2,false,4).take(10).toList
    //false = ascending = aufsteigend = wollen wir nicht d.h. wir bekommen absteigend
  }
  

  
    /* 
   * Calculate the number of requests per day.
   * Return a list of tuples which contain the day (1..30) as the first element and the number of
   * accesses as the second.
   * The list should be ordered by the day number.
   */
  
  def getNumberOfRequestsPerDay(data:RDD[Row]):List[(Int,Int)]= {
    val m = data.groupBy(x=>x.getAs[OffsetDateTime](3).getDayOfMonth).map(y=>(y._1, y._2.toList.length)).sortBy(z=>z._1, true, 4).collect().toList
    return m
  }
  

  
    /* 
   * Calculate the number of hosts that accesses the web server in June 95.
   * Every hosts should only be counted once.
   */
  
  def numberOfUniqueHosts(data:RDD[Row]):Long= {
    val accesses = data.groupBy(x=>x.getAs[String](0)).keys.collect().toList.length
    return accesses
  }
  
  /**
   * data.map(f=>f.getString(0)).distinct().count
   */
  

  
    /* 
   * Calculate the number of hosts per day that accesses the web server.
   * Every host should only be counted once per day.
   * Order the list by the day number.
   */
      
  def numberOfUniqueDailyHosts(data:RDD[Row]):List[(Int,Int)]= {
    val m = data.groupBy(x=>x.getAs[OffsetDateTime](3).getDayOfMonth).map(y=>(y._1,y._2.groupBy(z=>z.getString(0)).keys.toList.length)).sortBy(a=>a._1, true, 4).collect().toList
    return m
  }
  

  
    /*
   * Calculate the average number of requests per host for each single day.
   * Order the list by the day number.
   */
  
  def averageNrOfDailyRequestsPerHost(data:RDD[Row]):List[(Int,Int)]= {
    val m = data.groupBy(x=>x.getAs[OffsetDateTime](3).getDayOfMonth).map(y=>(y._1,y._2.toList.length.toInt / y._2.groupBy(z=>z.getString(0)).keys.toList.length.toInt)).sortBy(a=>a._1, true, 4).collect().toList
        
        //reduce((a:Double, b:Double)=>a*(1.toDouble/y._2.toList.length) + b*(1.toDouble/y._2.toList.length))))
    return m
  }
  

  
      /*
     * Calculate the top 25 hosts that causes error codes (Response Code=404)
     * Return a set of tuples consisting the hostnames  and the number of requests
     */
  
  
        /* Row composition
   * 
   * 0 - String: IP or name  
   * 1 - String: Client ID on user machine
   * 2 - String: User name
   * 3 - OffsetDateTime: Date and time of request
   * 4 - String: Request Method
   * 5 - String: Request endpoint
   * 6 - String: Protocol
   * 7 - Integer: Response Code
   * 8 - Integer: Content size
   * 
   */

  def top25ErrorCodeResponseHosts(data:RDD[Row]):Set[(String,Int)]= {
    val n = data.groupBy(x=>x.getString(0)).map(y=>(y._1,y._2.groupBy(z=>z.getInt(7)).getOrElse(404, Iterable.empty).toList.length)).sortBy(b=>b._2).take(25).toList.toSet
    return n
  }

  
    /*
   * Calculate the number of error codes (Response Code=404) per day.
   * Return a list of tuples that contain the day as the first element and the number as the second. 
   * Order the list by the day number.
   */

  
  def responseErrorCodesPerDay(data:RDD[Row]):List[(Int,Int)]= {
    
    val m = data.groupBy(x=>x.getAs[OffsetDateTime](3).getDayOfMonth).
    map(y=>(y._1,y._2.groupBy(z=>z.getInt(7)).getOrElse(404, Iterable.empty).toList.length)).
    sortBy(a=>a._1, true, 4).collect().toList
    return m
  }
  

  
    /*
   * Calculate the error response coded for every hour of the day.
   * Return a list of tuples that contain the hour as the first element (0..23) abd the number of error codes as the second.
   * Ergebnis soll eine Liste von Tupeln sein, deren erstes Element die Stunde bestimmt (0..23) und 
   * Order the list by the hour-number.
   */
  
  def errorResponseCodeByHour(data:RDD[Row]):List[(Int,Int)]= {
    val m = data.groupBy(x=>x.getAs[OffsetDateTime](3).getHour).map(y=>(y._1,y._2.groupBy(z=>z.getInt(7)).getOrElse(404, Iterable.empty).toList.length)).sortBy(a=>a._1, true).collect().toList
    return m
  }

      /* Row composition
   * 
   * 0 - String: IP or name  
   * 1 - String: Client ID on user machine
   * 2 - String: User name
   * 3 - OffsetDateTime: Date and time of request
   * 4 - String: Request Method
   * 5 - String: Request endpoint
   * 6 - String: Protocol
   * 7 - Integer: Response Code
   * 8 - Integer: Content size
   * 
   */
  
    /*		AVERAGE
   * Calculate the number of requests per weekday (Monday, Tuesday,...).
   * Return a list of tuples that contain the number of requests as the first element and the weekday
   * (String) as the second.
   * The elements should have the following order: [Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday].
   */
  
  def getAvgRequestsPerWeekDay(data:RDD[Row]):List[(Int,String)]=
    
    // MAP RETURNED EINE LISTE, DESHALB PACKEN WIR SELBER ES JA DANN AUCH IMMER IN EIN TUPEL () .. z.B. => (a._1,a._2) damit wir eine Liste von Tupel bekommen
    
    // Ergebnis ist immer um 2 Wochentage verschoben
    
    // 1. group by zur Gruppierung nach Wochen tagen 2. map um diesen Wochentagen die Durchschnittlichen Zugriffszahlen zuzuordnerne 3. in dieser map 3.1: Anzahl der Zugriffe an diesen Wochentag gesamt geteilt durch 3.2 (group by) die Menge der verschiedenen Datums der Wochentage aka wie viele Montage gab es 4. sortieren 5. map um Namen auf zu lösen ....
    
    data.groupBy(x=>x.getAs[OffsetDateTime](3).getDayOfWeek).map(y=>(y._2.toList.length / y._2.groupBy(b=>b.getAs[OffsetDateTime](3).getDayOfMonth).keys.toList.length,y._1)).sortBy(z=>z._2.getValue, true, 4).map(a=>(a._1,a._2.toString().toLowerCase().capitalize)).collect().toList
    
    
    //.sortBy(z=>z._2.getValue, true, 4)
    

}
