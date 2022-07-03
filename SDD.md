# System Design Document

The following system design document will provide an overview over the system design of the Garching Airlines flight infotainment system. This SDD will describe the essential parts of the client-server-architecture and its functionality, including descriptions of functional and nonfunctional requirements which were provided by the head EIST tutors.

The target audience of the Garching Airlines infotainment system are any potential plane passengers who use the system to browse for flights, book them and thus plan out their trips. Moreover, the system provides assistance for in-flight services, which means that anyone flying with a plane equipped with the GAIS is part of the target audience.

### Definitions:

* *GAIS*: Garching Airlines Infotainment System

## Overview

1. Executive Summary
2. System Overview
3. Future Contingencies
4. Design Guidelines
5. Design Considerations
6. System Architecture and Architecture Design
7. Design Specification










## Requirements

### Functional Requirements:

* **FR1: Show  flight  information:** The systemshows  flight  information (flight  number,  start time, end time, gate, terminal, seat, airplane type, airline, etc.)of all the user’s flightsand notifiesthe user if a flight is canceled or delayed.

* **FR2: Add new flight trip:** The user cansearch through available flights and can select the one’s he has booked.The user can build flight journeys (trips) with multiple connecting flights or one return flight and can see the trips on a map.

* **FR3: Display destination information:** The user can display important information such as  POIs  in  the  destination  place  (e.g.,hotels,  restaurants,  attractions)  and  weather  data. POIs should be shown in a map and the user should be able to filter them. When clickingon a POI, the user can see additional information and save the POI in the favorites.
  
* **FR4: Give feedback:** A passenger can give feedback after he took the flight about the flight itself, catering, entertainment, service orcomfort. Passengers who take the survey will be rewarded, e.g.,by miles, souvenirs, coupons,or price drawings.

* **FR5: Request service:** During a flight, a passenger can request the service. This notifies the service staff so that they can come to the passenger’s seat.


### Non-functional Requirements:

* **NFR1: Usability:** The systemshould be intuitive to use,and the user interface should be easy to understand. All interactions should be completed in less than three clicks.

* **NFR2: Conformance to guidelines:** The design of the systemshould conform to the typical usability guidelines such as Nielsen’s usability heuristics.

* **NFR3: Serversystem:** Aserversubsystem with a couple of services must be used in the system.However,  additional  services  like  destination  information  for  weather and  POIs shouldbe obtained from external services.


