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
import {
    Pagination,
    PaginationContent,
    PaginationEllipsis,
    PaginationItem,
    PaginationLink,
    PaginationNext,
    PaginationPrevious,
} from "@/components/ui/pagination"

// This is mock data. In a real application, you'd fetch this from an API.
const mockEvents: Event[] = [
    {
        id: "1",
        name: "Summer Ultimate Tournament",
        type: "Tournament",
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
        type: "Training",
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
        type: "Trial",
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
        type: "Tournament",
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
        type: "Social",
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
        type: "Tournament",
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
        type: "Training",
        description: "A week-long camp introducing youth to various disc sports.",
        date: new Date("2024-06-15"),
        registrationDetails: "Ages 10-16, limited spots available",
        creatorTeam: { id: "5", name: "Disc Sports Association" },
        creatorUser: null,
        participants: []
    }
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

    const totalPages = Math.ceil(filteredEvents.length / ITEMS_PER_PAGE)
    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE
    const paginatedEvents = filteredEvents.slice(startIndex, startIndex + ITEMS_PER_PAGE)

    const handleRegister = (eventId: string) => {
        // In a real application, you'd call an API to register the user
        console.log(`Registered for event with ID: ${eventId}`)
        // For this example, we'll just log the registration
    }

    return (
        <div className="container mx-auto py-12">
            <h1 className="text-3xl font-bold mb-8">Upcoming Events</h1>
            <div className="flex flex-col md:flex-row gap-4 mb-6">
                <Input
                    type="text"
                    placeholder="Search events..."
                    value={searchTerm}
                    onChange={(e) => {
                        setSearchTerm(e.target.value)
                        setCurrentPage(1)  // Reset to first page on new search
                    }}
                    className="max-w-sm"
                />
                <Select
                    value={typeFilter}
                    onValueChange={(value) => {
                        setTypeFilter(value as EventType | 'All')
                        setCurrentPage(1)  // Reset to first page on new filter
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
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
                {paginatedEvents.map((event) => (
                    <EventCard key={event.id} event={event} onRegister={handleRegister} />
                ))}
            </div>
            {filteredEvents.length > ITEMS_PER_PAGE && (
                <Pagination>
                    <PaginationContent>
                        <PaginationItem>
                            <PaginationPrevious
                                href="#"
                                onClick={() => setCurrentPage(prev => Math.max(prev - 1, 1))}
                                aria-disabled={currentPage === 1}
                            />
                        </PaginationItem>
                        {[...Array(totalPages)].map((_, i) => (
                            <PaginationItem key={i}>
                                <PaginationLink
                                    href="#"
                                    onClick={() => setCurrentPage(i + 1)}
                                    isActive={currentPage === i + 1}
                                >
                                    {i + 1}
                                </PaginationLink>
                            </PaginationItem>
                        ))}
                        <PaginationItem>
                            <PaginationNext
                                href="#"
                                onClick={() => setCurrentPage(prev => Math.min(prev + 1, totalPages))}
                                aria-disabled={currentPage === totalPages}
                            />
                        </PaginationItem>
                    </PaginationContent>
                </Pagination>
            )}
            {filteredEvents.length === 0 && (
                <p className="text-center text-muted-foreground mt-8">No events found matching your search criteria.</p>
            )}
        </div>
    )
}

