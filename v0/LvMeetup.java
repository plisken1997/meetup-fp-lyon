class LvMeeutp {

  class MeetupUser {
    public MeetupUser(int id, String name, int age, int bestFriend) {
      this.id = id;
      this.name = name;
      this.age = age;
      this.bestFriend = bestFriend;
    }
  }

  public LvMeeutp(LvMeetupRepository lvMeetupRepository) {
    this.lvMeetupRepository = lvMeetupRepository;
  }

  public MeetupUser findMeetupUser(int id) {
    return this.lvMeetupRepository.find(id);
  }

  public void displayAge(MeetupUser user) {
    System.io.println("%s %d", user.name, user.age);
  }

  public void displayUser(MeetupUser user) {
    System.io.println("Welcome %d", user.name);
  }

  public MeetupUser findBestFriend(MeetupUser user) {
    if (user.bestFriend == null) {
      return null;
    }

    return findMeetupUser(user.bestFriend);
  }

  public void displayAgeForUser(int userId) {
    MeetupUser user = this.findMeetupUser(userId);

    if (user != null) {
      displayAge(user);
      displayUser(user);
      MeetupUser bestFriend = findBestFriend(user);
      if (bestFriend != null) {
        displayAge(user);
        displayUser(user);
      }
    } else {
      System.io.println("Unknown user %s", userId);
    }
  }

}