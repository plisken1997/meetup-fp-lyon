<?php

function ln()
{
    echo "\n";
}

class MeetupGuest
{
    function __construct($id, $name, $age, array $techno, $bestFriend = null)
    {
        $this->id = $id;
        $this->name = $name;
        $this->age = $age;
        $this->techno = $techno;
        $this->bestFriend = $bestFriend;
    }

    function printInfo($printType)
    {
        switch ($printType) {
            case 'printAge':
                echo sprintf('%s is %d', $this->name, $this->age);
                break;
            case 'printName':
                echo sprintf('Welcome %s', $this->name);
                break;
            case 'printTechno':
                echo implode('', $this->techno);
                break;
            default:
                echo print('%s is invalid');
        }
    }
}

$scala = "scala";
$java = "java";
$php = "php";
$rust = "rust";

$guests = [
    new MeetupGuest(1, "Rocky", 38, [$scala, $java], 2),
    new MeetupGuest(2, "Apollo", 42, [$rust, $php, $java], null),
    new MeetupGuest(3, "Adrienne", 34, [$php, $rust], 1),
    new MeetupGuest(4, "Drago", 26, [$rust], null),
    new MeetupGuest(5, "Mary Anne", 32, [$java, $php], 2)
];

function findMeetupGuest($id, array $guests)
{
    $askedGuests = array_filter($guests, function (MeetupGuest $guest) use ($id) {
        return $id === $guest->id;
    });

    if (!count($askedGuests)) {
        return null;
    }

    return current($askedGuests);
}

function findBestFriend(MeetupGuest $user, array $guests)
{
    return findMeetupGuest($user->bestFriend, $guests);
}

function processBestFriend(MeetupGuest $guest, $guests)
{
    if (!$theBestFriend = findBestFriend($guest, $guests)) {
        echo sprintf("%s is sad and alone", $guest->name);
        return;
    }

    echo $theBestFriend->printInfo('printName');
    ln();
    echo $theBestFriend->printInfo('printAge');
    ln();

    return processBestFriend($theBestFriend, $guests);
}

processBestFriend($guests[0], $guests);
ln();
