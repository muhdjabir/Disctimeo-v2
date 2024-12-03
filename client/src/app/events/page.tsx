'use client'

import { useState } from 'react'
import { Event, EventType } from "@/types/event"
import { EventCard } from "@/components/cards/EventCard"
import { Input } from "@/components/ui/input"
import {
    Select,
    SelectContent,
    SelectItem,
    SelectTrigger,
    SelectValue,
} from "@/components/ui/select"
import { PaginatedList } from '@/components/lists/PaginatedList'
import { CreateEventForm } from '@/components/forms/CreateEventForm'
import { Club, TeamType } from '@/types/club'

// This is mock data. In a real application, you'd fetch this from an API.
const mockEvents: Event[] = [
    {
        id: "1",
        name: "Summer Ultimate Tournament",
        type: EventType.Tournament,
        description: "Annual summer ultimate frisbee tournament for all skill levels.",
        date: new Date("2023-07-15"),
        registrationDetails: "Open until July 1st, 2023",
        creatorTeam: { id: "1", name: "Disc Dynamos" },
        creatorUser: null,
        participants: [
            { id: "1", name: "John Doe", email: "john@example.com" },
            { id: "2", name: "Jane Smith", email: "jane@example.com" },
        ]
    },
    {
        id: "2",
        name: "Beginner's Disc Golf Workshop",
        type: EventType.Training,
        description: "Learn the basics of disc golf in this hands-on workshop.",
        date: new Date("2023-08-05"),
        registrationDetails: "Limited spots available, register by August 1st",
        creatorTeam: null,
        creatorUser: { id: "3", name: "Mike Johnson", email: "mike@example.com" },
        participants: []
    },
    // Add more mock events as needed to test pagination
    {
        id: "3",
        name: "Ultimate Frisbee League Tryouts",
        type: EventType.Trial,
        description: "Tryouts for the upcoming Ultimate Frisbee league season.",
        date: new Date("2023-09-01"),
        registrationDetails: "Open to all skill levels, pre-registration required",
        creatorTeam: { id: "2", name: "Flying Discs" },
        creatorUser: null,
        participants: []
    },
    {
        id: "4",
        name: "Disc Golf Tournament",
        type: EventType.Tournament,
        description: "Annual disc golf tournament with multiple divisions.",
        date: new Date("2023-10-15"),
        registrationDetails: "Registration closes on October 1st",
        creatorTeam: { id: "3", name: "Disc Golf Pros" },
        creatorUser: null,
        participants: []
    },
    {
        id: "5",
        name: "Freestyle Frisbee Exhibition",
        type: EventType.Social,
        description: "Watch and learn from freestyle frisbee experts.",
        date: new Date("2023-11-20"),
        registrationDetails: "Free entry, no registration required",
        creatorTeam: null,
        creatorUser: { id: "4", name: "Sarah Lee", email: "sarah@example.com" },
        participants: []
    },
    {
        id: "6",
        name: "Winter Indoor Ultimate League",
        type: EventType.Tournament,
        description: "Indoor Ultimate Frisbee league running through the winter months.",
        date: new Date("2023-12-01"),
        registrationDetails: "Team registration open until November 15th",
        creatorTeam: { id: "4", name: "Indoor Flyers" },
        creatorUser: null,
        participants: []
    },
    {
        id: "7",
        name: "Youth Disc Sports Camp",
        type: EventType.Training,
        description: "A week-long camp introducing youth to various disc sports.",
        date: new Date("2024-06-15"),
        registrationDetails: "Ages 10-16, limited spots available",
        creatorTeam: { id: "5", name: "Disc Sports Association" },
        creatorUser: null,
        participants: []
    }
]

// Mock teams data
const mockTeams: Club[] = [
    {
        id: "1",
        name: "Disc Dynamos",
        description: "A vibrant community of disc enthusiasts pushing the boundaries of the sport.",
        establishedDate: "2015",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: TeamType.Ultimate,
        members: [
            { id: "101", name: "Alice Smith", role: "Captain", avatarUrl: "/avatars/alice.png" },
            { id: "102", name: "Bob Johnson", role: "Player", avatarUrl: "/avatars/bob.png" },
        ],
        events: [
            { id: "201", name: "Dynamos Trial Day", date: "2024-12-01", type: "Trial" },
            { id: "202", name: "Ultimate Summer Tournament", date: "2024-06-15", type: "Tournament" },
        ],
    },
    {
        id: "2",
        name: "Sky Spinners",
        description: "Dedicated to mastering the art of aerial disc maneuvers and competitive play.",
        establishedDate: "2018",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: TeamType.Freestyle,
        members: [
            { id: "103", name: "Charlie Brown", role: "Coach", avatarUrl: "/avatars/charlie.png" },
            { id: "104", name: "Dana White", role: "Performer", avatarUrl: "/avatars/dana.png" },
        ],
        events: [
            { id: "203", name: "Freestyle Workshop", date: "2024-07-10", type: "Trial" },
            { id: "204", name: "Spin Masters Championship", date: "2024-09-20", type: "Tournament" },
        ],
    },
    // Add more mock teams as needed
]

const ITEMS_PER_PAGE = 6

export default function EventsPage() {
    const [events, setEvents] = useState<Event[]>(mockEvents)
    const [searchTerm, setSearchTerm] = useState('')
    const [typeFilter, setTypeFilter] = useState<EventType | 'All'>('All')
    const [currentPage, setCurrentPage] = useState(1)

    const filteredEvents = events.filter(event =>
        (event.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
            event.description.toLowerCase().includes(searchTerm.toLowerCase())) &&
        (typeFilter === 'All' || event.type === typeFilter)
    )

    const handleRegister = (eventId: string) => {
        console.log(`Registered for event with ID: ${eventId}`)
    }

    const handleEventCreated = (newEvent: Event) => {
        setEvents(prevEvents => [...prevEvents, newEvent])
    }

    return (
        <div className="container mx-auto py-12">
            <div className="flex justify-between items-center mb-8">
                <h1 className="text-3xl font-bold">Upcoming Events</h1>
                <CreateEventForm onEventCreated={handleEventCreated} Clubs={mockTeams} />
            </div>
            <div className="flex flex-col md:flex-row gap-4 mb-6">
                <Input
                    type="text"
                    placeholder="Search events..."
                    value={searchTerm}
                    onChange={(e) => {
                        setSearchTerm(e.target.value)
                        setCurrentPage(1)
                    }}
                    className="max-w-sm"
                />
                <Select
                    value={typeFilter}
                    onValueChange={(value) => {
                        setTypeFilter(value as EventType | 'All')
                        setCurrentPage(1)
                    }}
                >
                    <SelectTrigger className="max-w-[180px]">
                        <SelectValue placeholder="Select event type" />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectItem value="All">All Types</SelectItem>
                        <SelectItem value="Tournament">Tournament</SelectItem>
                        <SelectItem value="Trial">Trial</SelectItem>
                        <SelectItem value="Training">Training</SelectItem>
                        <SelectItem value="Social">Social</SelectItem>
                    </SelectContent>
                </Select>
            </div>
            <PaginatedList
                items={filteredEvents}
                itemsPerPage={ITEMS_PER_PAGE}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                renderItem={(event) => (
                    <EventCard key={event.id} event={event} onRegister={handleRegister} />
                )}
                noItemsMessage="No events found matching your search criteria."
            />
        </div>
    )
}


