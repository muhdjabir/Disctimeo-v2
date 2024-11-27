import { notFound } from 'next/navigation'
import { Club } from "@/types/club"
import { MemberList } from "@/components/lists/MemberList"
import { EventList } from "@/components/lists/EventList"
import { Badge } from "@/components/ui/badge"
import Image from "next/image"

// This is a mock function to fetch club data. In a real app, you'd fetch this from an API.
async function getClub(id: string): Promise<Club | null> {
    // This is mock data. Replace with actual API call in a real application.
    const club: Club = {
        id: "1",
        name: "Disc Dynamos",
        description: "A vibrant community of disc enthusiasts pushing the boundaries of the sport.",
        establishedDate: "2015",
        logoUrl: "/placeholder.svg?height=100&width=100",
        teamType: "Ultimate",
        members: [
            { id: "1", name: "John Doe", role: "Captain", avatarUrl: "/placeholder.svg?height=40&width=40" },
            { id: "2", name: "Jane Smith", role: "Co-Captain", avatarUrl: "/placeholder.svg?height=40&width=40" },
            { id: "3", name: "Mike Johnson", role: "Member", avatarUrl: "/placeholder.svg?height=40&width=40" },
        ],
        events: [
            { id: "1", name: "Summer Tournament", date: "2023-07-15", type: "Tournament" },
            { id: "2", name: "New Player Trials", date: "2023-08-01", type: "Trial" },
        ]
    }

    return club.id === id ? club : null
}

export default async function ClubPage({ params }: { params: { id: string } }) {
    const club = await getClub(params.id)

    if (!club) {
        notFound()
    }

    return (
        <div className="container mx-auto py-12">
            <div className="flex items-center space-x-4 mb-8">
                <div className="relative w-20 h-20">
                    <Image
                        src={club.logoUrl}
                        alt={`${club.name} logo`}
                        layout="fill"
                        objectFit="cover"
                        className="rounded-full"
                    />
                </div>
                <div>
                    <h1 className="text-3xl font-bold">{club.name}</h1>
                    <Badge variant="secondary" className="mt-2">{club.teamType}</Badge>
                </div>
            </div>

            <div className="grid gap-8 md:grid-cols-2">
                <div>
                    <h2 className="text-xl font-semibold mb-4">About the Club</h2>
                    <p className="text-muted-foreground mb-4">{club.description}</p>
                    <p className="text-sm text-muted-foreground">Established {club.establishedDate}</p>
                </div>

                <div className="space-y-8">
                    <MemberList members={club.members} />
                    <EventList events={club.events} />
                </div>
            </div>
        </div>
    )
}

