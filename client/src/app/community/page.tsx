'use client'

import { useState } from 'react'
import { Player } from "@/types/player"
import { PlayerCard } from "@/components/cards/PlayerCard"
import { Input } from "@/components/ui/input"
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
const mockPlayers: Player[] = [
    {
        id: "1",
        username: "discmaster",
        email: "discmaster@example.com",
        position: "Handler",
        startedPlaying: 2015,
        age: 28,
        club: "Disc Dynamos"
    },
    {
        id: "2",
        username: "skywalker",
        email: "skywalker@example.com",
        position: "Cutter",
        startedPlaying: 2018,
        age: 24,
        club: "Sky Spinners"
    },
    {
        id: "3",
        username: "zonebreaker",
        email: "zonebreaker@example.com",
        position: "Mid",
        startedPlaying: 2010,
        age: 32,
        club: "Zone Breakers"
    },
    {
        id: "4",
        username: "speedster",
        email: "speedster@example.com",
        position: "Cutter",
        startedPlaying: 2019,
        age: 22,
        club: "Disc Dynamos"
    },
    {
        id: "5",
        username: "discgolfpro",
        email: "discgolfpro@example.com",
        position: "All-around",
        startedPlaying: 2008,
        age: 35,
        club: "Disc Golf Masters"
    },
    {
        id: "6",
        username: "ultimatewarrior",
        email: "ultimatewarrior@example.com",
        position: "Handler",
        startedPlaying: 2016,
        age: 27,
        club: "Ultimate Legends"
    },
    {
        id: "7",
        username: "freestyler",
        email: "freestyler@example.com",
        position: "Freestyle",
        startedPlaying: 2014,
        age: 29,
        club: "Freestyle Flyers"
    },
    // Add more mock players as needed to test pagination
]

const ITEMS_PER_PAGE = 6

export default function CommunityPage() {
    const [players, setPlayers] = useState<Player[]>(mockPlayers)
    const [searchTerm, setSearchTerm] = useState('')
    const [currentPage, setCurrentPage] = useState(1)

    const filteredPlayers = players.filter(player =>
        player.username.toLowerCase().includes(searchTerm.toLowerCase()) ||
        player.position.toLowerCase().includes(searchTerm.toLowerCase()) ||
        player.club.toLowerCase().includes(searchTerm.toLowerCase())
    )

    const totalPages = Math.ceil(filteredPlayers.length / ITEMS_PER_PAGE)
    const startIndex = (currentPage - 1) * ITEMS_PER_PAGE
    const paginatedPlayers = filteredPlayers.slice(startIndex, startIndex + ITEMS_PER_PAGE)

    return (
        <div className="container mx-auto py-12">
            <h1 className="text-3xl font-bold mb-8">Community Players</h1>
            <div className="mb-6">
                <Input
                    type="text"
                    placeholder="Search players by username, position, or club..."
                    value={searchTerm}
                    onChange={(e) => {
                        setSearchTerm(e.target.value)
                        setCurrentPage(1)  // Reset to first page on new search
                    }}
                    className="max-w-md"
                />
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-8">
                {paginatedPlayers.map((player) => (
                    <PlayerCard key={player.id} player={player} />
                ))}
            </div>
            {filteredPlayers.length > ITEMS_PER_PAGE && (
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
            {filteredPlayers.length === 0 && (
                <p className="text-center text-muted-foreground mt-8">No players found matching your search criteria.</p>
            )}
        </div>
    )
}

