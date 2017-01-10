<?php

class MeetupGuest
{
    function __construct($name, $age, array $techno)
    {
        $this->name = $name;
        $this->age = $age;
        $this->techno = $techno;
    }

    function printInfo($printType)
    {
        switch ($printType) {
            case 'printAge':
                echo $this->age;
                break;
            case 'printName':
                echo $this->name;
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
    new MeetupGuest("Rocky", 38, [$scala, $java]),
    new MeetupGuest("Apollo", 42, [$rust, $php, $java]),
    new MeetupGuest("Adrienne", 34, [$php, $rust]),
    new MeetupGuest("Drago", 26, [$rust]),
    new MeetupGuest("Mary Anne", 32, [$java, $php])
];

$printActions = ['printAge', 'printName', 'printTechno'];

foreach ($printActions as $printAction) {
    foreach ($guests as $guest) {
        $guest->printInfo($printAction);
        ln();
    }
    ln();
}

function ln()
{
    echo "\n";
}
