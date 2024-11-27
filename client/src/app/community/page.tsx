'use client'

import { useState } from 'react'
import { Player } from "@/types/player"
import { PlayerCard } from "@/components/cards/PlayerCard"
import { Input } from "@/components/ui/input"
import { PaginatedList } from '@/components/lists/PaginatedList'

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
                        setCurrentPage(1)
                    }}
                    className="max-w-md"
                />
            </div>
            <PaginatedList
                items={filteredPlayers}
                itemsPerPage={ITEMS_PER_PAGE}
                currentPage={currentPage}
                setCurrentPage={setCurrentPage}
                renderItem={(player) => (
                    <PlayerCard key={player.id} player={player} />
                )}
                noItemsMessage="No players found matching your search criteria."
            />
        </div>
    )
}