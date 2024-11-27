'use client'

import { useState } from 'react'
import { Club } from "@/types/club"
import { ClubCard } from "@/components/cards/ClubCard"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
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

// This is placeholder data. In a real application, you'd fetch this from an API.
const allClubs: Club[] = [
    {
        id: "1",
        name: "Disc Dynamos",
        description: "A vibrant community of disc enthusiasts pushing the boundaries of the sport.",
        establishedDate: "2015",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Ultimate",
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
        teamType: "Freestyle",
        members: [
            { id: "103", name: "Charlie Brown", role: "Coach", avatarUrl: "/avatars/charlie.png" },
            { id: "104", name: "Dana White", role: "Performer", avatarUrl: "/avatars/dana.png" },
        ],
        events: [
            { id: "203", name: "Freestyle Workshop", date: "2024-07-10", type: "Trial" },
            { id: "204", name: "Spin Masters Championship", date: "2024-09-20", type: "Tournament" },
        ],
    },
    {
        id: "3",
        name: "Frisbee Fanatics",
        description: "A close-knit group of players fostering friendship through disc sports.",
        establishedDate: "2010",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Mixed",
        members: [
            { id: "105", name: "Eva Green", role: "Player", avatarUrl: "/avatars/eva.png" },
            { id: "106", name: "Frank Black", role: "Manager", avatarUrl: "/avatars/frank.png" },
        ],
        events: [
            { id: "205", name: "Fanatics Open Day", date: "2024-05-01", type: "Trial" },
            { id: "206", name: "Mixed Disc Festival", date: "2024-08-30", type: "Tournament" },
        ],
    },
    {
        id: "4",
        name: "Ultimate Legends",
        description: "Striving for excellence in Ultimate Frisbee competitions at all levels.",
        establishedDate: "2005",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Ultimate",
        members: [
            { id: "107", name: "George Wilson", role: "Captain", avatarUrl: "/avatars/george.png" },
            { id: "108", name: "Helen Brown", role: "Player", avatarUrl: "/avatars/helen.png" },
        ],
        events: [
            { id: "207", name: "Legends Invitational", date: "2024-04-15", type: "Tournament" },
        ],
    },
    {
        id: "5",
        name: "Disc Golf Masters",
        description: "Perfecting the art of disc golf one hole at a time.",
        establishedDate: "2012",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Disc Golf",
        members: [
            { id: "109", name: "Ian Parker", role: "Golfer", avatarUrl: "/avatars/ian.png" },
        ],
        events: [
            { id: "208", name: "Masters Golf Open", date: "2024-11-20", type: "Tournament" },
        ],
    },
    {
        id: "6",
        name: "Freestyle Flyers",
        description: "Pushing the boundaries of freestyle disc performances.",
        establishedDate: "2019",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Freestyle",
        members: [
            { id: "110", name: "Jane Doe", role: "Performer", avatarUrl: "/avatars/jane.png" },
            { id: "111", name: "Kyle Smith", role: "Performer", avatarUrl: "/avatars/kyle.png" },
        ],
        events: [
            { id: "209", name: "Flyers Performance Night", date: "2024-03-05", type: "Trial" },
        ],
    },
    {
        id: "7",
        name: "All-Round Disc Club",
        description: "Embracing all forms of disc sports under one roof.",
        establishedDate: "2008",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Mixed",
        members: [
            { id: "112", name: "Liam Jones", role: "Player", avatarUrl: "/avatars/liam.png" },
            { id: "113", name: "Mia Davis", role: "Player", avatarUrl: "/avatars/mia.png" },
        ],
        events: [
            { id: "210", name: "Disc Club Annual Meetup", date: "2024-02-15", type: "Trial" },
            { id: "211", name: "All-Round Championships", date: "2024-12-10", type: "Tournament" },
        ],
    },
];


const ITEMS_PER_PAGE = 6

export default function ClubsPage() {
    const [searchTerm, setSearchTerm] = useState('')
    const [currentPage, setCurrentPage] = useState(1)
    const [teamTypeFilter, setTeamTypeFilter] = useState<Club['teamType'] | 'All'>('All')

    const filteredClubs = allClubs.filter(club =>
        (club.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
            club.description.toLowerCase().includes(searchTerm.toLowerCase())) &&
        (teamTypeFilter === 'All' || club.teamType === teamTypeFilter)
    )

    const totalPages = Math.ceil(filteredClubs.length / ITEMS_PER_PAGE)
    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE
    const paginatedClubs = filteredClubs.slice(startIndex, startIndex + ITEMS_PER_PAGE)

    return (
        <div className="container mx-auto py-12">
            <h1 className="text-3xl font-bold mb-8">Frisbee Clubs</h1>
            <div className="flex flex-col md:flex-row gap-4 mb-6">
                <Input
                    type="text"
                    placeholder="Search clubs..."
                    value={searchTerm}
                    onChange={(e) => {
                        setSearchTerm(e.target.value)
                        setCurrentPage(1)  // Reset to first page on new search
                    }}
                    className="max-w-sm"
                />
                <Select
                    value={teamTypeFilter}
                    onValueChange={(value) => {
                        setTeamTypeFilter(value as Club['teamType'] | 'All')
                        setCurrentPage(1)  // Reset to first page on new filter
                    }}
                >
                    <SelectTrigger className="max-w-[180px]">
                        <SelectValue placeholder="Select team type" />
                    </SelectTrigger>
                    <SelectContent>
                        <SelectItem value="All">All Types</SelectItem>
                        <SelectItem value="Ultimate">Ultimate</SelectItem>
                        <SelectItem value="Disc Golf">Disc Golf</SelectItem>
                        <SelectItem value="Freestyle">Freestyle</SelectItem>
                        <SelectItem value="Mixed">Mixed</SelectItem>
                    </SelectContent>
                </Select>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
                {paginatedClubs.map((club) => (
                    <ClubCard key={club.id} club={club} />
                ))}
            </div>
            {filteredClubs.length > ITEMS_PER_PAGE && (
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
            {filteredClubs.length === 0 && (
                <p className="text-center text-muted-foreground">No clubs found matching your search and filter criteria.</p>
            )}
        </div>
    )
}

