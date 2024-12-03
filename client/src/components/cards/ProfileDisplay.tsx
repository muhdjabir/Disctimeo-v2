import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "../ui/button"
import React from "react"

interface UserProfile {
    id: string
    username: string
    email: string
    startedPlaying: number
    position: string
    teams: string[]
    events: {
        past: string[]
        upcoming: string[]
    }
}

interface ProfileDisplayProps {
    profile: UserProfile
    setIsEditing: React.Dispatch<React.SetStateAction<boolean>>
}

export function ProfileDisplay({ profile, setIsEditing }: ProfileDisplayProps) {
    return (
        <Card>
            <CardHeader>
                <CardTitle>{profile.username}</CardTitle>
            </CardHeader>
            <CardContent>
                <div className="grid gap-4">
                    <div>
                        <strong>Email:</strong> {profile.email}
                    </div>
                    <div>
                        <strong>Started Playing:</strong> {profile.startedPlaying}
                    </div>
                    <div>
                        <strong>Position:</strong> {profile.position}
                    </div>
                    <div>
                        <strong>Teams:</strong>
                        <div className="flex flex-wrap gap-2 mt-2">
                            {profile.teams.map((team, index) => (
                                <Badge key={index} variant="secondary">{team}</Badge>
                            ))}
                        </div>
                    </div>
                    <div>
                        <strong>Upcoming Events:</strong>
                        <ul className="list-disc list-inside mt-2">
                            {profile.events.upcoming.map((event, index) => (
                                <li key={index}>{event}</li>
                            ))}
                        </ul>
                    </div>
                    <div>
                        <strong>Past Events:</strong>
                        <ul className="list-disc list-inside mt-2">
                            {profile.events.past.map((event, index) => (
                                <li key={index}>{event}</li>
                            ))}
                        </ul>
                    </div>
                </div>
            </CardContent>
            <CardFooter className="flex justify-end">
                <Button
                    onClick={() => setIsEditing(true)}
                >
                    Edit Profile
                </Button>
            </CardFooter>
        </Card>
    )
}

